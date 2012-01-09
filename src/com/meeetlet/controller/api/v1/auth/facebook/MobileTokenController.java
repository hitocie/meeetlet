package com.meeetlet.controller.api.v1.auth.facebook;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;

public class MobileTokenController extends TokenController {

    private Logger log = Logger.getLogger(getClass().getName());

    
    @Override
    public Navigation run() throws Exception {
        
        String token = getAccessToken();
        if (token != null) {
            login(token);
            log.info("Your device is mobile.");
            
            this.response.getWriter().write("");
        }
        
        return null;
    }
}
