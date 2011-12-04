package com.meeetlet.service.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.meeetlet.common.event.Response;
import com.meeetlet.meta.event.EventMeta;
import com.meeetlet.meta.event.ParticipantMeta;
import com.meeetlet.meta.event.PreEventMeta;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;
import com.meeetlet.model.event.Participant;
import com.meeetlet.model.event.PreEvent;


public class EventService {

    public List<Event> getEvents(int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        return Datastore.query(m)
                .filter(filters.toArray(new FilterCriterion[filters.size()]))
                .sort(m.timestamp.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public List<Event> getMyEvents(Key myKey, int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        filters.add(m.ownerRef.equal(myKey));
        return Datastore.query(m)
                .filter(filters.toArray(new FilterCriterion[filters.size()]))
                .sort(m.timestamp.desc)
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
    
    public Event createEvent(
            User owner, 
            Event event, 
            List<User> participants,
            PreEvent preEvent) throws Exception {

        event.setKey(Datastore.allocateId(EventMeta.get()));
        event.setEventid(event.getKey().getId() + "");
        
        event.getOwnerRef().setKey(owner.getKey());
        event.setTimestamp(new Date()); // now

        Transaction tx = Datastore.beginTransaction();
        try {

            // pre event
            if (preEvent != null) {
                Key key = Datastore.createKey(
                    event.getKey(), PreEventMeta.get(), 1);
                preEvent.setKey(key);
                Datastore.put(tx, preEvent);
                
                event.getPreEventRef().setKey(preEvent.getKey()); // Event.preEventRef
            }
            // participants
            for (int i = 0; i < participants.size(); i++) {
                Participant participant = new Participant();
                participant.getUserRef().setModel(participants.get(i));
                Key key = Datastore.createKey(
                    event.getKey(), ParticipantMeta.get(), i + 1);
                participant.setKey(key);
                participant.getEventRef().setModel(event);
                Datastore.put(tx, participant);
            }
            // event
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
        PreEvent preEvent = event.getPreEventRef().getModel();
        
        Transaction tx = Datastore.beginTransaction();
        try {
            for (Participant p : event.getParticipantsRef().getModelList())
                Datastore.delete(tx, p.getKey());
            if (preEvent != null)
                Datastore.delete(tx, preEvent.getKey());
            Datastore.delete(tx, event.getKey());
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    
    // TODO: Working on this method.
    public Event replyEvent(User user, Event event, Response attend, String comment) throws Exception {
        
        Participant participant = null;
        for (Participant p : event.getParticipantsRef().getModelList()) {
            if (user.getKey().equals(p.getUserRef().getKey())) {
                p.setAttend(attend);
                p.setComment(comment);
                participant = p;
                break;
            }
        }

        if (participant == null)
            throw new Exception("Not exists the matched Participant."); // FIXME
        
        event.setTimestamp(new Date());
        
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx, event, participant);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        return event;
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
