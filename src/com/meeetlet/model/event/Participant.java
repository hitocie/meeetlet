package com.meeetlet.model.event;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.model.common.User;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

@Model(schemaVersion = 1)
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
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
    

    private String comment;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
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
        return new JSONObject()
        .put("user", userRef.getModel().toJSONObject())
        .put("comment", comment);
    }
}
