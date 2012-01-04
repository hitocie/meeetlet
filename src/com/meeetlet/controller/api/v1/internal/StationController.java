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
import com.meeetlet.model.common.Station;
import com.meeetlet.service.common.AreaService;

public class StationController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Navigation run() throws Exception {

        // TODO: security check (only admin)

        response.setContentType(Const.charEncoding);

        log.info("/api/v1/internal/station");
        AreaService as = new AreaService();

        JSONArray jStations = 
                JSONUtil.inputStreamToJSONArray(request.getInputStream());
        List<Station> stations = new ArrayList<Station>();
        for (int i = 0; i < jStations.length(); i++) {
            JSONObject jStation = jStations.getJSONObject(i);
            Station station = new Station();
            station.setName(jStation.getString("name"));
            station.setYomi(jStation.getString("yomi"));
            station.setLine(jStation.getString("line"));
            station.setLat(jStation.getDouble("lat"));
            station.setLng(jStation.getDouble("lng"));
            station.setPrefecture(jStation.getString("pref"));
            stations.add(station);
        }
        as.createStations(stations);
        response.getWriter().write("null");

        return null;
    }
}
