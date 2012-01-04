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
        prefs.put("01", "�k�C��");
        prefs.put("02", "�X��");
        prefs.put("03", "��茧");
        prefs.put("04", "�{�錧");
        prefs.put("05", "�H�c��");
        prefs.put("06", "�R�`��");
        prefs.put("07", "������");
        prefs.put("08", "��錧");
        prefs.put("09", "�Ȗ،�");
        prefs.put("10", "�Q�n��");
        prefs.put("11", "��ʌ�");
        prefs.put("12", "��t��");
        prefs.put("13", "�����s");
        prefs.put("14", "�_�ސ쌧");
        prefs.put("15", "�V����");
        prefs.put("16", "�x�R��");
        prefs.put("17", "�ΐ쌧");
        prefs.put("18", "���䌧");
        prefs.put("19", "�R����");
        prefs.put("20", "���쌧");
        prefs.put("21", "�򕌌�");
        prefs.put("22", "�É���");
        prefs.put("23", "���m��");
        prefs.put("24", "�O�d��");
        prefs.put("25", "���ꌧ");
        prefs.put("26", "���s�{");
        prefs.put("27", "���{");
        prefs.put("28", "���Ɍ�");
        prefs.put("29", "�ޗǌ�");
        prefs.put("30", "�a�̎R��");
        prefs.put("31", "���挧");
        prefs.put("32", "������");
        prefs.put("33", "���R��");
        prefs.put("34", "�L����");
        prefs.put("35", "�R����");
        prefs.put("36", "������");
        prefs.put("37", "���쌧");
        prefs.put("38", "���Q��");
        prefs.put("39", "���m��");
        prefs.put("40", "������");
        prefs.put("41", "���ꌧ");
        prefs.put("42", "���茧");
        prefs.put("43", "�F�{��");
        prefs.put("44", "�啪��");
        prefs.put("45", "�{�茧");
        prefs.put("46", "��������");
        prefs.put("47", "���ꌧ");

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
