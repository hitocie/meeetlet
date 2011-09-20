package com.meeetlet.common;

import java.io.Serializable;
import java.util.List;

import com.meeetlet.model.common.User;

public abstract class Me implements Serializable {

    private static final long serialVersionUID = 1L;

    public String accessToken;
    public User user;

    abstract public List<User> getFriends();
    
    public Me(String accessToken) {
        this.accessToken = accessToken;
    }
}
