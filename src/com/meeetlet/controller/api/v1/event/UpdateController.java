package com.meeetlet.controller.api.v1.event;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Const;
import com.meeetlet.common.Me;
import com.meeetlet.model.event.Event;
import com.meeetlet.service.event.EventService;

public class UpdateController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {

        response.setContentType(Const.charEncoding);

        String service = asString("service");
        log.info("/api/v1/event/update?service=" + service);
        if (service != null) {

            Me me = (Me) sessionScope("me");
            if (me != null) {
                
                EventService es = new EventService();
                if (service.equals("create_event")) {
                    // service=create_event
                    Event e = new Event();
                    e.setTitle(asString("title"));
                    e.setEventDate(asDate("eventDate", "yyyy-MM-dd HH:mm"));
                    e.setPlace(asString("place"));
                    e.setBudget(asString("budget"));
                    e.setGenre(asString("genre"));
                    // TODO:
                    JSONArray participants = new JSONArray(asString("participants"));
                    // create friend as User class if not exsits??
                    //------
                    e.setComment(asString("comment"));

                    e = es.createEvent(me.getUser(), e);
                    e.toJSONObject().write(response.getWriter());

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
                    
                } else if (service.equals("delete")) {
                    // service=delete
                    es.deleteEvent(asString("eventid"));
                    response.getWriter().write("null");
                    
                    return null;
                }
            }
        }

        return forward("../error/no_service.html");
    }
}

