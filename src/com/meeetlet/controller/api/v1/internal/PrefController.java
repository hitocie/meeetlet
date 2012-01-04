package com.meeetlet.controller.api.v1.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.Const;
import com.meeetlet.model.common.Prefecture;
import com.meeetlet.service.common.AreaService;

public class PrefController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public Navigation run() throws Exception {

        // TODO: security check (only admin)

        response.setContentType(Const.charEncoding);

        log.info("/api/v1/internal/pref");
        AreaService as = new AreaService();
        Map<String, String> prefs = new HashMap<String, String>();
        prefs.put("01", "–kŠC“¹");
        prefs.put("02", "ÂXŒ§");
        prefs.put("03", "ŠâèŒ§");
        prefs.put("04", "‹{éŒ§");
        prefs.put("05", "H“cŒ§");
        prefs.put("06", "RŒ`Œ§");
        prefs.put("07", "•Ÿ“‡Œ§");
        prefs.put("08", "ˆïéŒ§");
        prefs.put("09", "“È–ØŒ§");
        prefs.put("10", "ŒQ”nŒ§");
        prefs.put("11", "é‹ÊŒ§");
        prefs.put("12", "ç—tŒ§");
        prefs.put("13", "“Œ‹“s");
        prefs.put("14", "_“ŞìŒ§");
        prefs.put("15", "VŠƒŒ§");
        prefs.put("16", "•xRŒ§");
        prefs.put("17", "ÎìŒ§");
        prefs.put("18", "•ŸˆäŒ§");
        prefs.put("19", "R—œŒ§");
        prefs.put("20", "’·–ìŒ§");
        prefs.put("21", "Šò•ŒŒ§");
        prefs.put("22", "Ã‰ªŒ§");
        prefs.put("23", "ˆ¤’mŒ§");
        prefs.put("24", "OdŒ§");
        prefs.put("25", " ‰êŒ§");
        prefs.put("26", "‹“s•{");
        prefs.put("27", "‘åã•{");
        prefs.put("28", "•ºŒÉŒ§");
        prefs.put("29", "“Ş—ÇŒ§");
        prefs.put("30", "˜a‰ÌRŒ§");
        prefs.put("31", "’¹æŒ§");
        prefs.put("32", "“‡ªŒ§");
        prefs.put("33", "‰ªRŒ§");
        prefs.put("34", "L“‡Œ§");
        prefs.put("35", "RŒûŒ§");
        prefs.put("36", "“¿“‡Œ§");
        prefs.put("37", "ìŒ§");
        prefs.put("38", "ˆ¤•QŒ§");
        prefs.put("39", "‚’mŒ§");
        prefs.put("40", "•Ÿ‰ªŒ§");
        prefs.put("41", "²‰êŒ§");
        prefs.put("42", "’·èŒ§");
        prefs.put("43", "ŒF–{Œ§");
        prefs.put("44", "‘å•ªŒ§");
        prefs.put("45", "‹{èŒ§");
        prefs.put("46", "­™“‡Œ§");
        prefs.put("47", "‰«“êŒ§");

        List<Prefecture> prefectures = new ArrayList<Prefecture>();
        for (String p : prefs.keySet()) {
            Prefecture prefecture = new Prefecture();
            prefecture.setCode(p);
            prefecture.setName(prefs.get(p));
            prefectures.add(prefecture);
        }
        as.createPrefectures(prefectures);

        response.getWriter().write("null");

        return null;
    }
}
