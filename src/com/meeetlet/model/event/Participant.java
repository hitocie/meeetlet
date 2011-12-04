package com.meeetlet.model.event;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.common.event.Response;
import com.meeetlet.model.common.User;

@Model(schemaVersion = 1)
public class Participant implements Serializable {

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

    //-------------------------
    // User relation (user)
    private ModelRef<User> userRef = new ModelRef<User>(User.class);
    public ModelRef<User> getUserRef() {
        return userRef;
    }
    
    // Event relation
    private ModelRef<Event> eventRef = new ModelRef<Event>(Event.class);
    public ModelRef<Event> getEventRef() {
        return eventRef;
    }
    
    // attend :Response
    private Response attend;
    public Response getAttend() {
        return attend;
    }
    public void setAttend(Response attend) {
        this.attend = attend;
    }

    // comment :String
    private String comment;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    // PreParticipant relation (preParticipant)
    private ModelRef<PreParticipant> preParticipantRef = new ModelRef<PreParticipant>(PreParticipant.class);
    public ModelRef<PreParticipant> getPreParticipantRef() {
        return preParticipantRef;
    }
    //-------------------------
    
    
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
        Participant other = (Participant) obj;
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
        PreParticipant pp = preParticipantRef.getModel();

        return new JSONObject()
        .put("user", userRef.getModel().toJSONObject())
        .put("participants", (pp == null ? null : pp.toJSONObject()))
        .put("comment", comment);
    }   
}