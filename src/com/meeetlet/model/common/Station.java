package com.meeetlet.model.common;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.common.utils.DateUtil;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Station implements Serializable {

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

    //-----
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String yomi;
    public String getYomi() {
        return yomi;
    }
    public void setYomi(String yomi) {
        this.yomi = yomi;
    }
    
    private String line;
    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    
    private String prefecture;
    public String getPrefecture() {
        return prefecture;
    }
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    private double lat;
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    private double lng;
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    private Date timestamp;
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    //-----

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
        Station other = (Station) obj;
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
        //.put("key", KeyFactory.keyToString(key))
        .put("name", name)
        .put("yomi", yomi)
        .put("line", line)
        .put("prefecture", prefecture)
        .put("lat", lat)
        .put("lng", lng)
        .put("timestamp", DateUtil.toString(timestamp));
    }
}
