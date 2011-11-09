package com.meeetlet.service.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.meeetlet.meta.event.EventMeta;
import com.meeetlet.meta.event.ParticipantMeta;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;
import com.meeetlet.model.event.Participant;


public class EventService {

    public List<Event> getEvents(boolean withHistory, int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        if (withHistory)
            filters.add(m.expiredDate.greaterThan(new Date()));
        return Datastore.query(m)
                .filter(filters.toArray(new FilterCriterion[filters.size()]))
                .sort(m.registeredDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public List<Event> getMyEvents(Key myKey, boolean withHistory, int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        filters.add(m.ownerRef.equal(myKey));
        if (withHistory)
            filters.add(m.expiredDate.greaterThan(new Date()));
        return Datastore.query(m)
                .filter(filters.toArray(new FilterCriterion[filters.size()]))
                .sort(m.expiredDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public Event getEvent(String eventid) {
        EventMeta m = EventMeta.get();
        return Datastore.query(m)
                .filter(m.eventid.equal(eventid))
                .asSingle();
    }
    
    public Event createEvent(User owner, Event event) throws Exception {

        event.setKey(Datastore.allocateId(EventMeta.get()));
        event.setEventid(event.getKey().getId() + "");
        
        event.getOwnerRef().setKey(owner.getKey());

        Date now = new Date();
        event.setRegisteredDate(now);
        event.setTimestamp(now);

        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, event);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        return event;
    }
    
    public void deleteEvent(String eventid) throws Exception {
        
        Event event = getEvent(eventid);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for (Participant p : event.getParticipantsRef().getModelList()) {
                Datastore.delete(tx, p.getKey());
            }
            Datastore.delete(tx, event.getKey());
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    
    public Event joinEvent(User user, String comment, Event event) throws Exception {
        
        // TODO: If this user has already joined, it should throw exception. 
        //ParticipantMeta m = ParticipantMeta.get();
        //Participant p = Datastore.query(m)
        //        .filter(m.eventRef.equal(event.getKey()), m.userRef.equal(user.getKey()))
        //        .asSingle();
        //if (p != null)
        //    throw error;
        
        Participant p = new Participant();
        p.getUserRef().setModel(user);
        p.setComment(comment);
        Key key = Datastore.createKey(
            event.getKey(), 
            ParticipantMeta.get(), 
            event.getParticipantsRef().getModelList().size() + 1);
        p.setKey(key);
        p.getEventRef().setModel(event);
        
        event.setTimestamp(new Date());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, event, p);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        return event;
    }

    public Event cancelEvent(User user, Event event) throws Exception {
        
        event.setTimestamp(new Date());
        
        ParticipantMeta m = ParticipantMeta.get();
        Participant p = Datastore.query(m)
                .filter(m.eventRef.equal(event.getKey()), m.userRef.equal(user.getKey()))
                .asSingle();
        
        if (p == null)
            return event; // TODO: if this user has not joined, error?
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.delete(tx, p.getKey());
            Datastore.put(tx, event);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        return event;
    }
}
