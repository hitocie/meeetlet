package com.meeetlet.controller.api.v1.test;

import java.text.DateFormat;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.local.MeForLocal;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;
import com.meeetlet.service.common.UserService;
import com.meeetlet.service.event.EventService;

public class IndexController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());

    
    @Override
    public Navigation run() throws Exception {

        // NOTE: for test
        String service = asString("service");
        log.info("/test/?service=" + service);
        if (service != null) {
            if (service.equals("login")) {
                
                sessionScope("me", new MeForLocal(asString("userid")));
                return null;
                
            } else if (service.equals("create_test_data")) {

                // users
                UserService userService = new UserService();

                User user1 = new User();
                user1.setUserid("100000");
                user1.setUsername("Katsushi Fukui");
                user1.setToken("token:" + user1.getToken());
                userService.createUser(user1);

                User user2 = new User();
                user2.setUserid("200000");
                user2.setUsername("Hitoshi Okada");
                user2.setToken("token:" + user2.getToken());
                userService.createUser(user2);

                User user3 = new User();
                user3.setUserid("300000");
                user3.setUsername("Foo Foo");
                user3.setToken("token:" + user3.getToken());
                userService.createUser(user3);


                // events
                List<User> users = userService.getUsers();
                EventService eventService = new EventService();
                for (int i = 0; i < 5; i++) {
                    Event e = new Event();
                    e.setSubject("Event" + i);
                    e.setPlace("‹âÀ");
                    e.setNumber(10);
                    e.setEventDate(DateFormat.getDateInstance().parse("2011/10/10"));
                    e.setExpiredDate(DateFormat.getDateInstance().parse("2011/11/11"));
                    e.setComment("This is a comment.");
                    eventService.createEvent(users.get(0), e);
                }
                return null;

            } else if (service.equals("delete_test_users")) {

            }
        }

        return forward("test.html");
    }
}

