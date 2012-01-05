package com.meeetlet.controller.api.v1.common;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.meeetlet.common.Const;
import com.meeetlet.common.Me;
import com.meeetlet.model.common.City;
import com.meeetlet.model.common.Prefecture;
import com.meeetlet.model.common.Station;
import com.meeetlet.service.common.AreaService;

public class AreaController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Navigation run() throws Exception {

        response.setContentType(Const.charEncoding);

        Me me = (Me) sessionScope("me");
        if (me != null) {
            String service = asString("service");
            log.info("/api/v1/common/area?service=" + service);
            if (service != null) {
                AreaService as = new AreaService();
                
                if (service.equals("all_prefectures")) {
                    
                    List<Prefecture> prefs = as.getAllPrefectures();
                    JSONArray out = new JSONArray();
                    for (Prefecture p : prefs) {
                        out.put(p.toJSONObject());
                    }
                    out.write(response.getWriter());
                    
                } else if (service.startsWith("cities_by_")) {
                    
                    List<City> cities = null;
                    if (service.equals("cities_by_prefecture"))
                        cities = as.getCities(asString("prefecture"));
                    else if (service.equals("cities_by_name"))
                        cities = as.findCities(asString("name"));
                    
                    if (cities != null) {
                        JSONArray out = new JSONArray();
                        for (City c : cities) {
                            out.put(c.toJSONObject());
                        }
                        out.write(response.getWriter());
                        
                        // FIXME: else => Exception(?)
                    }
                    
                } else if (service.startsWith("stations_by_")) {
                    
                    List<Station> stations = null;
                    if (service.equals("stations_by_prefecture"))
                        stations = as.getStations(asString("prefecture"));
                    else if (service.equals("stations_by_name"))
                        stations = as.findStations(asString("name"));
                    
                    if (stations != null) {
                        JSONArray out = new JSONArray();
                        for (Station s : stations) {
                            out.put(s.toJSONObject());
                        }
                        out.write(response.getWriter());
                        
                        // FIXME: else => Exception(?)
                    } 
                }
            }
        }

        return null;
    }
}
