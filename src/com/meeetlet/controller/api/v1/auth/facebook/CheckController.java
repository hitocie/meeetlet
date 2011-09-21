package com.meeetlet.controller.api.v1.auth.facebook;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.Me;

public class CheckController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());


    @Override
    public Navigation run() throws Exception {
        
        String service = asString("service");
        if (service != null) {
            log.info("service=" + service);
            if (service.equals("login")) {
                Me me = (Me) sessionScope("me");
                response.getWriter().write((me != null) + "");
                return null;
            }
        }
        
        return null;
    }
}
