package com.meeetlet.model.event;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.common.utils.DateUtil;
import com.meeetlet.meta.event.ParticipantMeta;
import com.meeetlet.model.common.User;

@Model(schemaVersion = 1)
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }

    @Attribute(version = true)
    private Long version;
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    
    // -------------------------------
    // event id
    private String eventid;
    public String getEventid() {
        return eventid;
    }
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    // title
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // event date
    private Date eventDate;
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    
    // place
    private String place;
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    
    // budget :String
    private String budget;
    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    
    // genre :String(?)
    private String genre;
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    // shop :String
    private String shop;
    public String getShop() {
        return shop;
    }
    public void setShop(String shop) {
        this.shop = shop;
    }
    
    // User relation (owner)
    private ModelRef<User> ownerRef = new ModelRef<User>(User.class);
    public ModelRef<User> getOwnerRef() {
        return ownerRef;
    }

    // Participant relation (participants)
    @Attribute(persistent=false)
    private InverseModelListRef<Participant, Event> participantsRef =
        new InverseModelListRef<Participant, Event>(Participant.class, ParticipantMeta.get().eventRef, this);
    public InverseModelListRef<Participant, Event> getParticipantsRef() {
        return participantsRef;
    }

    // comment
    private String comment;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    // PreEvent relation (preEvent)
    private ModelRef<PreEvent> preEventRef = new ModelRef<PreEvent>(PreEvent.class);
    public ModelRef<PreEvent> getPreEventRef() {
        return preEventRef;
    }
    
    // isCanceled
    private boolean canceled;
    public boolean isCanceled() {
        return canceled;
    }
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    
    // timestamp
    private Date timestamp;
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    // -------------------------------


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
    
    
    
    public JSONObject toJSONObject() throws JSONException {
        JSONArray participants = new JSONArray();
        for (Participant p : getParticipantsRef().getModelList()) {
            participants.put(p.toJSONObject());
        }
        
        return new JSONObject()
        //.put("key", KeyFactory.keyToString(key))
        .put("id", eventid)
        .put("title", title)
        .put("eventDate", DateUtil.toString(eventDate))
        .put("place", place)
        .put("budget", budget)
        .put("genre", genre)
        .put("shop", shop)
        .put("owner", ownerRef.getModel().toJSONObject())
        .put("participants", participants)
        .put("comment", comment)
        .put("preEvent", (preEventRef.getModel() == null ? null  : preEventRef.getModel().toJSONObject()))
        .put("canceled", canceled)
        .put("timestamp", DateUtil.toString(timestamp));
    }
}
