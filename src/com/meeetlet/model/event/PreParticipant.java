package com.meeetlet.model.event;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.common.event.Response;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class PreParticipant implements Serializable {

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

    //---------------
    // dates :Array<Response>
    private List<Response> dates;
    public List<Response> getDates() {
        return dates;
    }
    public void setDates(List<Response> dates) {
        this.dates = dates;
    }

    // places :Array<Response>
    private List<Response> places;
    public List<Response> getPlaces() {
        return places;
    }
    public void setPlaces(List<Response> places) {
        this.places = places;
    }
    
    // budges :Array<Response>
    private List<Response> budgets;
    public List<Response> getBudgets() {
        return budgets;
    }
    public void setBudgets(List<Response> budgets) {
        this.budgets = budgets;
    }
    
    // genres :Array<Response>
    private List<Response> genres;
    public List<Response> getGenres() {
        return genres;
    }
    public void setGenres(List<Response> genres) {
        this.genres = genres;
    }

    // comment :String
    private String comment;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    //---------------
    
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
        PreParticipant other = (PreParticipant) obj;
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
        JSONArray jdates = new JSONArray();
        for (Response r : dates) {
            jdates.put(r);
        }
        JSONArray jplaces = new JSONArray();
        for (Response r : places) {
            jplaces.put(r);
        }
        JSONArray jbudgets = new JSONArray();
        for (Response r : budgets) {
            jbudgets.put(r);
        }
        JSONArray jgenres = new JSONArray();
        for (Response r : genres) {
            jgenres.put(r);
        }
                
        return new JSONObject()
        .put("dates", jdates)
        .put("places", jplaces)
        .put("budgets", jbudgets)
        .put("genres", jgenres)
        .put("comment", comment);
    }   
}
