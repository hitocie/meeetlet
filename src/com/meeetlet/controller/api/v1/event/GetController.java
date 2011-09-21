package com.meeetlet.controller.api.v1.event;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Me;
import com.meeetlet.model.event.Event;
import com.meeetlet.service.event.EventService;

public class GetController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {

        String service = asString("service");
        log.info("/api/v1/event/get?service=" + service);
        if (service != null) {

            int offset = (asInteger("offset") == null ? 0 : asInteger("offset"));
            int limit = (asInteger("limit") == null ? 20 : asInteger("limit"));
            boolean withHistory = (asBoolean("withHistory") != null ? asBoolean("withHistory") : false);

            if (service.equals("all_events")) {
                EventService es = new EventService();
                List<Event> events = 
                        (withHistory ? es.getEventsWithHistory(offset, limit) : es.getEvents(offset, limit));
                JSONArray out = new JSONArray();
                for (Event e : events)
                    out.put(e.toJSONObject());
                out.write(response.getWriter());

                return null;
            }


            Me me = (Me) sessionScope("me");
            if (me != null) {
                if (service.equals("my_events")) {
                    EventService es = new EventService();
                    List<Event> events =
                            (withHistory ? es.getMyEventsWithHistory(me.getUser().getKey(), offset, limit) : es.getMyEvents(me.getUser().getKey(), offset, limit));
                    JSONArray out = new JSONArray();
                    for (Event e : events)
                        out.put(e.toJSONObject());
                    out.write(response.getWriter());

                    return null;
                }
            }
        }

        return forward("../error/no_service.html");
    }
}