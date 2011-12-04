package com.meeetlet.controller.api.v1.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Const;
import com.meeetlet.common.Me;
import com.meeetlet.common.utils.DateUtil;
import com.meeetlet.common.utils.MeUtil;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;
import com.meeetlet.model.event.PreEvent;
import com.meeetlet.service.common.UserService;
import com.meeetlet.service.event.EventService;

public class UpdateController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    private List<User> makeParticipants(Me me, String json) throws Exception {
        JSONArray jparticipants = new JSONArray(json);
        // Create User class for friend if not exists.
        UserService us = new UserService();
        List<User> participants = new ArrayList<User>();
        for (int i = 0; i < jparticipants.length(); i++) {
            String userid = jparticipants.getString(i);
            User user = us.getUser(userid);
            if (user == null) {
                List<User> friends = me.getFriends();
                user = us.createUser(MeUtil.findFriend(friends, userid));
            }
            participants.add(user);
        }
        return participants;
    }

    @Override
    public Navigation run() throws Exception {

        response.setContentType(Const.charEncoding);

        String service = asString("service");
        log.info("/api/v1/event/update?service=" + service);
        if (service != null) {

            Me me = (Me) sessionScope("me");
            if (me != null) {

                EventService es = new EventService();
                if (service.equals("create_pre_event")) {
                    // service=create_pre_event
                    Event e = new Event();
                    e.setTitle(asString("title"));
                    List<User> participants = makeParticipants(me, asString("participants"));

                    PreEvent pe = new PreEvent();
                    pe.setDates(new ArrayList<Date>());
                    JSONArray jdates = new JSONArray(asString("eventDates"));
                    for (int i = 0; i < jdates.length(); i++)
                        pe.getDates().add(DateUtil.toDate(jdates.getString(i)));

                    pe.setPlaces(new ArrayList<String>());
                    JSONArray jplaces = new JSONArray(asString("places"));
                    for (int i = 0; i < jplaces.length(); i++)
                        pe.getPlaces().add(jplaces.getString(i));

                    pe.setBudgets(new ArrayList<String>());
                    JSONArray jbudgets = new JSONArray(asString("budgets"));
                    for (int i = 0; i < jbudgets.length(); i++)
                        pe.getBudgets().add(jbudgets.getString(i));

                    pe.setGenres(new ArrayList<String>());
                    JSONArray jgenres = new JSONArray(asString("genres"));
                    for (int i = 0; i < jgenres.length(); i++)
                        pe.getGenres().add(jgenres.getString(i));

                    e = es.createEvent(me.getUser(), e, participants, pe);
                    e.toJSONObject().write(response.getWriter());

                    return null;

                } else if (service.equals("create_event")) {
                    // service=create_event
                    Event e = new Event();
                    e.setTitle(asString("title"));
                    e.setEventDate(asDate("eventDate", "yyyy-MM-dd HH:mm"));
                    e.setPlace(asString("place"));
                    e.setBudget(asString("budget"));
                    e.setGenre(asString("genre"));
                    List<User> participants = makeParticipants(me, asString("participants"));
                    e.setComment(asString("comment"));

                    e = es.createEvent(me.getUser(), e, participants, null);
                    e.toJSONObject().write(response.getWriter());

                    return null;

                } else if (service.equals("delete_event")) {
                    // service=delete_event
                    es.deleteEvent(asString("eventid"));
                    response.getWriter().write("null");

                    return null;

                } else if (service.equals("join_event")) {
                    // service=join_event
                    Event e = es.getEvent(asString("eventid"));

                    e = es.joinEvent(me.getUser(), asString("comment"), e);
                    e.toJSONObject().write(response.getWriter());

                    return null;

                } else if (service.equals("cancel_event")) {
                    // service=cancel_event
                    Event e = es.getEvent(asString("eventid"));

                    e = es.cancelEvent(me.getUser(), e);
                    e.toJSONObject().write(response.getWriter());

                    return null;

                }
            }
        }

        return forward("../error/no_service.html");
    }
}

