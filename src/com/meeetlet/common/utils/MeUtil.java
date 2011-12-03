package com.meeetlet.common.utils;

import java.util.List;

import com.meeetlet.model.common.User;


public class MeUtil {

    public static User findFriend(List<User> friends, String id) {
        for (User friend : friends) {
            if (friend.getUserid().equals(id))
                return friend;
        }
        return null;
    }
}
