package com.meeetlet.common.facebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.meeetlet.common.Me;
import com.meeetlet.model.common.User;
import com.meeetlet.service.common.UserService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class MeForFacebook implements Me, Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    public User getUser() {
        return user;
    }
    
    private List<User> friends;
    public List<User> getFriends() {
        return friends;
    }
    

    public MeForFacebook(String token) throws Exception {
        
        FacebookClient client = new DefaultFacebookClient(token);
        com.restfb.types.User fbUser = 
                client.fetchObject("me", com.restfb.types.User.class);
        
        UserService us = new UserService();
        String userid = fbUser.getId();
        user = us.getUser(userid);
        if (user == null) {
            user = new User();
            user.setUserid(userid);
            user.setUsername(fbUser.getName());
            user.setToken(token);
            us.createUser(user);
        }

        List<com.restfb.types.User> fbFriends = 
                client.fetchConnection("me/friends", com.restfb.types.User.class).getData();
        friends = new ArrayList<User>();
        for (com.restfb.types.User fbf : fbFriends) {
            User u = new User();
            u.setUserid(fbf.getId());
            u.setUsername(fbf.getName());
            friends.add(u);
        }
    }
}
