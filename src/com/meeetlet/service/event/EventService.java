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
import com.meeetlet.meta.event.PreParticipantMeta;
import com.meeetlet.model.common.User;
import com.meeetlet.model.event.Event;
import com.meeetlet.model.event.Participant;
import com.meeetlet.model.event.PreEvent;
import com.meeetlet.model.event.PreParticipant;

public class EventService {
    
    private User me;
    
    
    static public List<Event> getEvents(int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        return Datastore.query(m)
                .filter(filters.toArray(new FilterCriterion[filters.size()]))
                .sort(m.timestamp.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }
    

    public List<Event> getMyEvents(int offset, int limit) {
        EventMeta m = EventMeta.get();
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();
        filters.add(m.ownerRef.equal(me.getKey()));
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

    private Participant getMyParticipant(Event event) throws Exception {
        Key myKey = me.getKey();
        for (Participant p : event.getParticipantsRef().getModelList()) {
            if (myKey.equals(p.getUserRef().getKey())) {
                return p;
            }
        }
        throw new Exception("Not exists the matched Participant."); // FIXME
    }

    public Event createEvent(
            Event event, 
            List<User> participants,
            PreEvent preEvent) throws Exception {

        event.setKey(Datastore.allocateId(EventMeta.get()));
        event.setEventid(event.getKey().getId() + "");

        event.getOwnerRef().setKey(me.getKey());
        event.setCanceled(false);
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
            participants.add(me); // add me.
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
        if (!event.getOwnerRef().getKey().equals(me.getKey()))
            throw new Exception("Could not delete the event for other person.");
        
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

    public Participant replyPreEvent(String eventid, PreParticipant preParticipant) throws Exception {

        Event event = getEvent(eventid);
        Participant participant = getMyParticipant(event);

        event.setTimestamp(new Date());

        Transaction tx = Datastore.beginTransaction();
        try {
            // pre participant
            Key key = Datastore.createKey(
                participant.getKey(), PreParticipantMeta.get(), 1);
            preParticipant.setKey(key);
            Datastore.put(tx, preParticipant);

            // participant
            participant.getPreParticipantRef().setKey(preParticipant.getKey()); // Participant.preParticipantRef
            Datastore.put(tx, participant);

            // event
            Datastore.put(tx, event);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }

        return participant;
    }

    public Event replyEvent(String eventid, Response attend, String comment) throws Exception {
        
        Event event = getEvent(eventid);
        Participant participant = getMyParticipant(event);
        participant.setAttend(attend);
        participant.setComment(comment);

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
    
    public Event inviteFriends(String eventid, List<User> friends) throws Exception {
        
        Event event = getEvent(eventid);
        event.setTimestamp(new Date());

        Transaction tx = Datastore.beginTransaction();
        try {
            int max = event.getParticipantsRef().getModelList().size() + 1;
            for (int i = 0; i < friends.size(); i++) {
                Participant participant = new Participant();
                participant.getUserRef().setModel(friends.get(i));
                Key key = Datastore.createKey(
                    event.getKey(), ParticipantMeta.get(), i + max);
                participant.setKey(key);
                participant.getEventRef().setModel(event);
                Datastore.put(tx, participant);
            }
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
    
    public Event cancelEvent(String eventid) throws Exception {
        
        Event event = getEvent(eventid);
        event.setCanceled(true);
        event.setTimestamp(new Date());

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
    
    
    // constructor 
    public EventService(User me) {
        this.me = me;
    }
}
