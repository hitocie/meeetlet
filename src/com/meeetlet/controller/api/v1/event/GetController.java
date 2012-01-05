package com.meeetlet.controller.api.v1.event;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Const;
import com.meeetlet.common.Me;
import com.meeetlet.model.event.Event;
import com.meeetlet.service.event.EventService;

public class GetController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {

        response.setContentType(Const.charEncoding);

        String service = asString("service");
        log.info("/api/v1/event/get?service=" + service);
        if (service != null) {

            int offset = (asInteger("offset") == null ? 0 : asInteger("offset"));
            int limit = (asInteger("limit") == null ? 20 : asInteger("limit"));

            // FIXME: Should not public "all_events"? 
            if (service.equals("all_events")) {
                List<Event> events = EventService.getEvents(offset, limit);
                JSONArray out = new JSONArray();
                for (Event e : events)
                    out.put(e.toJSONObject());
                out.write(response.getWriter());

                return null;
            }


            Me me = (Me) sessionScope("me");
            if (me != null) {
                List<Event> events = null;
                EventService es = new EventService(me.getUser());
                if (service.equals("my_events")) {
                    events = es.getMyEvents(offset, limit);
                } else if (service.equals("my_events_by_keyword")) {
                    events = es.findEvents(asString("keyword"), offset, limit);
                }
                if (events != null) {
                    JSONArray out = new JSONArray();
                    for (Event e : events) {
                        out.put(e.toJSONObject());
                    }
                    out.write(response.getWriter());
                } else {
                    response.getWriter().write("[]");
                }
                return null;
            }
        }

        return forward("../error/no_service.html");
    }
}