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

    public Participant getParticipant(User user, Event event) throws Exception {
        for (Participant p : event.getParticipantsRef().getModelList()) {
            if (user.getKey().equals(p.getUserRef().getKey())) {
                return p;
            }
        }

        throw new Exception("Not exists the matched Participant."); // FIXME
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

    public Participant replyPreEvent(User user, Event event, PreParticipant preParticipant) throws Exception {
        Participant participant = getParticipant(user, event);

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

    public Event replyEvent(User user, Event event, Response attend, String comment) throws Exception {
        Participant participant = getParticipant(user, event);
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
}
