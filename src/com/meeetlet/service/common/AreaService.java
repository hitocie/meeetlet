package com.meeetlet.service.common;

import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;
import com.meeetlet.meta.common.CityMeta;
import com.meeetlet.meta.common.PrefectureMeta;
import com.meeetlet.meta.common.StationMeta;
import com.meeetlet.model.common.City;
import com.meeetlet.model.common.Prefecture;
import com.meeetlet.model.common.Station;


public class AreaService {
    
    // Prefectures
    private void createPrefecture(Prefecture pref) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            pref.setKey(Datastore.allocateId(PrefectureMeta.get()));
            pref.setTimestamp(new Date());
            Datastore.put(tx, pref);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    private void deletePrefecture(Prefecture pref) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.delete(pref.getKey());
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    public void createPrefectures(/*Map<String, String> prefectures*/List<Prefecture> prefectures) throws Exception {
//        Transaction tx = Datastore.beginTransaction();
//        try {
//            // clear
//            for (Prefecture p : getAllPrefectures()) {
//                Datastore.delete(p.getKey());
//            }
//            
//            // create
////            for (String p : prefectures.keySet()) {
////                Prefecture prefecture = new Prefecture();
////                prefecture.setKey(Datastore.allocateId(PrefectureMeta.get()));
////                prefecture.setCode(p);
////                prefecture.setName(prefectures.get(p));
////                Datastore.put(tx, prefecture);
////            }
//            for (Prefecture pref : prefectures) {
//                pref.setKey(Datastore.allocateId(PrefectureMeta.get()));
//                Datastore.put(tx, pref);
//            }
//            tx.commit();
//        } catch (Exception e) {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            throw e;
//        }
        for (Prefecture p : getAllPrefectures())
            deletePrefecture(p);

        for (Prefecture p : prefectures)
            createPrefecture(p);
    }
    
    public List<Prefecture> getAllPrefectures() {
        PrefectureMeta m = PrefectureMeta.get();
        return Datastore.query(m)
                .sort(m.code.asc)
                .asList();
    }
    
    
    
    // Cities
    private void createCity(City city) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            city.setKey(Datastore.allocateId(CityMeta.get()));
            city.setTimestamp(new Date());
            Datastore.put(tx, city);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    private void deleteCity(City city) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.delete(city.getKey());
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    public void createCities(List<City> cities) throws Exception {
//        Transaction tx = Datastore.beginTransaction();
//        try {
//            // clear
//            for (City c : getAllCities()) {
//                Datastore.delete(c.getKey());
//            }
//            tx.commit();
//            // create
//            for (City city : cities) {
//                city.setKey(Datastore.allocateId(CityMeta.get()));
//                Datastore.put(tx, city);
//            }
//            tx.commit();
//        } catch (Exception e) {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            throw e;
//        }
        for (City c : getAllCities())
            deleteCity(c);

        for (City c : cities)
            createCity(c);
    }

    public List<City> getAllCities() {
        CityMeta m = CityMeta.get();
        return Datastore.query(m).asList();
    }
    
    public List<City> getCities(String prefecture) {
        CityMeta m = CityMeta.get();
        return Datastore.query(m)
                .filter(m.prefecture.equal(prefecture))
                .asList();
    }
    
    public List<City> findCities(String name) {
        CityMeta m = CityMeta.get();
        return Datastore.query(m)
                .filter(m.name.startsWith(name))
                .asList();
    }
    
    // Stations
    private void createStation(Station station) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            station.setKey(Datastore.allocateId(StationMeta.get()));
            station.setTimestamp(new Date());
            Datastore.put(tx, station);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    private void deleteStation(Station station) throws Exception {
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.delete(station.getKey());
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }
    public void createStations(List<Station> stations) throws Exception {
//        Transaction tx = Datastore.beginTransaction();
//        try {
//            // clear
//            for (Station s : getAllStations()) {
//                Datastore.delete(s.getKey());
//            }
//
//            // create
//            for (Station station : stations) {
//                station.setKey(Datastore.allocateId(StationMeta.get()));
//                Datastore.put(tx, station);
//            }
//            tx.commit();
//        } catch (Exception e) {
//            if (tx.isActive()) {
//                tx.rollback();
//            }
//            throw e;
//        }
        for (Station s : getAllStations())
            deleteStation(s);

        for (Station s : stations)
            createStation(s);
    }
    
    public List<Station> getAllStations() {
        StationMeta m = StationMeta.get();
        return Datastore.query(m).asList();
    }
    
    public List<Station> getStations(String prefecture) {
        StationMeta m = StationMeta.get();
        return Datastore.query(m)
                .filter(m.prefecture.equal(prefecture))
                .asList();
    }
    
    public List<Station> findStations(String name) {
        StationMeta m = StationMeta.get();
        return Datastore.query(m)
                .filter(m.name.startsWith(name))
                .asList();
    }
}
