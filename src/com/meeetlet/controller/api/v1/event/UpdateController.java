package com.meeetlet.controller.api.v1.event;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.Me;
import com.meeetlet.model.event.Event;
import com.meeetlet.model.event.Participant;
import com.meeetlet.service.event.EventService;

public class UpdateController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {


        String service = asString("service");
        log.info("/api/v1/event/update?service=" + service);
        if (service != null) {

            Me me = (Me) sessionScope("me");
            if (me != null) {
                
                EventService es = new EventService();
                if (service.equals("create")) {
                    // service=create
                    Event e = new Event();

                    e.setSubject(asString("subject"));
                    e.setEventDate(asDate("eventDate", "yyyy-MM-dd HH:mm"));
                    e.setExpiredDate(asDate("expiredDate", "yyyy-MM-dd HH:mm"));
                    e.setPlace(asString("place"));
                    e.setNumber(asInteger("number"));
                    e.setComment(asString("comment"));

                    e = es.createEvent(me.getUser(), e);
                    e.toJSONObject().write(response.getWriter());

                    return null;
                    
                } else if (service.equals("add_participant")) {
                    // service=add_participant
                    Event e = es.getEvent(asString("eventid"));
                    
                    Participant p = new Participant();
                    p.getUserRef().setModel(me.getUser());
                    p.setComment(asString("comment"));
                    
                    e = es.addParticipant(p, e);
                    e.toJSONObject().write(response.getWriter());
                    
                    return null;
                    
                } else if (service.equals("delete")) {
                    // service=delete
                    es.deleteEvent(asString("eventid"));
                    response.getWriter().write("");
                    
                    return null;
                }
            }
        }

        return forward("../error/no_service.html");
    }
}

