package com.meeetlet.common.local;

import java.io.Serializable;
import java.util.List;

import com.meeetlet.common.Me;
import com.meeetlet.model.common.User;
import com.meeetlet.service.common.UserService;

public class MeForLocal extends Me implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public List<User> getFriends() {
        // TODO:
        return null;
    }
    
    public MeForLocal(String userid) {
        super(null);
        
        UserService userService = new UserService();
        user = userService.getUser(userid);
    }
}
