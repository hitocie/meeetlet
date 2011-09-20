package com.meeetlet.common.facebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.meeetlet.common.Me;
import com.meeetlet.model.common.User;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class MeForFacebook extends Me implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<User> friends;
    public List<User> getFriends() {
        return friends;
    }
    

    public MeForFacebook(String accessToken) {
        
        super(accessToken);
        
        FacebookClient client = new DefaultFacebookClient(accessToken);
        user = new User();
        com.restfb.types.User fbUser = 
                client.fetchObject("me", com.restfb.types.User.class);
        user.setUserid(fbUser.getId());
        user.setUsername(fbUser.getName());

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
