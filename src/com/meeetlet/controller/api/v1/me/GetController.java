package com.meeetlet.controller.api.v1.me;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Me;
import com.meeetlet.model.common.User;

public class GetController extends Controller {
    
    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {

        String service = asString("service");
        log.info("/api/v1/me/get?service=" + service);
        if (service != null) {
            
            Me me = (Me) sessionScope("me");
            if (me != null) {

                if (service.equals("me")) {
                    // service=me
                    User user = me.user;
                    user.toJSONObject().write(response.getWriter());
                    return null;

                } else if (service.equals("my_friends")) {
                    // service=my_friends
                    List<User> friends = me.getFriends();
                    JSONArray out = new JSONArray();
                    for (User u : friends) 
                        out.put(u.toJSONObject());
                    out.write(response.getWriter());
                    return null;
                }
            }
        }

        return forward("../error/no_service.html");
    }

}
