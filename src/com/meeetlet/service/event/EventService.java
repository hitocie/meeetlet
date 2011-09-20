package com.meeetlet.service.event;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.meeetlet.meta.event.EventMeta;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;


public class EventService {

    public List<Event> getEvents(int offset, int limit) {
        EventMeta m = EventMeta.get();
        return Datastore.query(m)
                .filter(m.expiredDate.greaterThan(new Date()))
                .sort(m.registeredDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public List<Event> getEventsWithHistory(int offset, int limit) {
        EventMeta m = EventMeta.get();
        return Datastore.query(m)
                .sort(m.registeredDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public List<Event> getMyEvents(Key myKey, int offset, int limit) {
        EventMeta m = EventMeta.get();
        return Datastore.query(m)
                .filter(m.ownerRef.equal(myKey), m.expiredDate.greaterThan(new Date()))
                .sort(m.expiredDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public List<Event> getMyEventsWithHistory(Key myKey, int offset, int limit) {
        EventMeta m = EventMeta.get();
        return Datastore.query(m) 
                .filter(m.ownerRef.equal(myKey))
                .sort(m.eventid.desc)
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
    
    public void addParticipant(User participant, Event event) throws Exception {
        
        event.setTimestamp(new Date());

        participant.getEventRef().setModel(event);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, participant);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

}
