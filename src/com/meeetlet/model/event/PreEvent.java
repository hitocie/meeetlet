package com.meeetlet.model.event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

@Model(schemaVersion = 1)
public class PreEvent implements Serializable {

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
    
    //-------
    // TODO: Can I merge PreEvent to Event?
    // event (eventRef --> Event)
    //private ModelRef<Event> eventRef = new ModelRef<Event>(Event.class);
    //public ModelRef<Event> getEventRef() {
    //    return eventRef;
    //}
    
    // dates:Array (e.g. 11/23, 11/24, 11/25)
    private List<Date> dates;
    public List<Date> getDates() {
        return dates;
    }
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }
    
    // places:Array (e.g. Boston, NY, LA)
    private List<String> places;
    public List<String> getPlaces() {
        return places;
    }
    public void setPlaces(List<String> places) {
        this.places = places;
    }

    // budges:Array (e.g. 1000-2000, 2000-3000, 3000-4000)
    private List<String> budgets;
    public List<String> getBudgets() {
        return budgets;
    }
    public void setBudgets(List<String> budgets) {
        this.budgets = budgets;
    }
    
    // genres:Array (e.g. Japanese, Chinese, Western)
    private List<String> genres;
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    
    // shops:Array (e.g. A, B, C)
    private List<String> shops;
    public List<String> getShops() {
        return shops;
    }
    public void setShops(List<String> shops) {
        this.shops = shops;
    }
    //-------

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
        PreEvent other = (PreEvent) obj;
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
        for (Date r : dates) {
            jdates.put(r);
        }
        JSONArray jplaces = new JSONArray();
        for (String r : places) {
            jplaces.put(r);
        }
        JSONArray jbudgets = new JSONArray();
        for (String r : budgets) {
            jbudgets.put(r);
        }
        JSONArray jgenres = new JSONArray();
        for (String r : genres) {
            jgenres.put(r);
        }
        JSONArray jshops = new JSONArray();
        for (String r : shops) {
            jshops.put(r);
        }
        
        return new JSONObject()
        //.put("key", KeyFactory.keyToString(key))
        .put("dates", jdates)
        .put("places", jplaces)
        .put("budgets", jbudgets)
        .put("genres", jgenres)
        .put("shops", jshops);
    }

}
