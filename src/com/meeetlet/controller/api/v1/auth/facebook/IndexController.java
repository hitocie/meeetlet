package com.meeetlet.controller.api.v1.auth.facebook;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.facebook.App;

/*/api/v1/auth/ */
public class IndexController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());
    
    
    @Override
    public Navigation run() throws Exception {
        
        log.info("Start auth.");
        
        return redirect(
            "https://graph.facebook.com/oauth/authorize?client_id=" + App.appId + 
            "&redirect_uri=" + App.authSitePage +
            "&scope=publish_stream,offline_access,manage_pages");
    }
}
