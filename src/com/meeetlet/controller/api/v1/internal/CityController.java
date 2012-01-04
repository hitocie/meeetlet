package com.meeetlet.controller.api.v1.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.meeetlet.common.Const;
import com.meeetlet.common.utils.JSONUtil;
import com.meeetlet.model.common.City;
import com.meeetlet.service.common.AreaService;

public class CityController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Navigation run() throws Exception {

        // TODO: security check (only admin)
        
        response.setContentType(Const.charEncoding);

        log.info("/api/v1/internal/city");
        AreaService as = new AreaService();

        JSONArray jCities = JSONUtil.inputStreamToJSONArray(request.getInputStream());
        List<City> cities = new ArrayList<City>();
        for (int i = 0; i < jCities.length(); i++) {
            JSONObject jCity = jCities.getJSONObject(i);
            City city = new City();
            city.setName(jCity.getString("name"));
            city.setYomi(jCity.getString("yomi"));
            city.setPrefecture(jCity.getString("pref"));
            cities.add(city);
        }
        as.createCities(cities);
        response.getWriter().write("null");

        return null;
    }
}
