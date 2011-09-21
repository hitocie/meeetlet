package com.meeetlet.common.local;

import java.io.Serializable;
import java.util.List;

import com.meeetlet.common.Me;
import com.meeetlet.model.common.User;
import com.meeetlet.service.common.UserService;


public class MeForLocal implements Me, Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    public User getUser() {
        return user;
    }
    
    public List<User> getFriends() {
        // TODO:
        return null;
    }
    
    public MeForLocal(String userid) {
        UserService userService = new UserService();
        user = userService.getUser(userid);
    }
}
