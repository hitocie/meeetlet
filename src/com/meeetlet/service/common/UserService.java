package com.meeetlet.service.common;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.meeetlet.meta.common.UserMeta;
import com.meeetlet.model.common.User;


public class UserService {

    
    public User createUser(User user) throws Exception {
        
        user.setKey(Datastore.allocateId(UserMeta.get()));
        user.setTimestamp(new Date());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        return user;
    }
    
    public User updateUser(User user) throws Exception {
        
        user.setTimestamp(new Date());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        return user;
    }
    
    public void deleteUser(Key... keys) throws Exception {
       
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.delete(keys);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    
    public List<User> getUsers() {
        UserMeta m = UserMeta.get();
        return Datastore.query(m).asList();
    }
    
    public User getUser(String userid) {
        UserMeta m = UserMeta.get();
        return Datastore.query(m)
                .filter(m.userid.equal(userid))
                .asSingle();
    }
}
