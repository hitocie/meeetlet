package com.meeetlet.controller.api.v1.auth;

import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.meeetlet.common.Me;
import com.meeetlet.common.facebook.App;
import com.meeetlet.common.facebook.MeForFacebook;

/*/api/v1/auth/ */
public class IndexController extends Controller {

    private Logger log = Logger.getLogger(getClass().getName());
    
    
    private Me login(String accessToken) {
        // TODO: switch facebook/google+
        Me me = new MeForFacebook(accessToken);
        sessionScope("me", me);
        log.info(me.user.getUsername() + " logged in");
        return me;
    }
    
    @Override
    public Navigation run() throws Exception {
        
        String validationCode = asString("code");
        log.info("validation code=" + validationCode);

        String uri = 
                "https://graph.facebook.com/oauth/access_token?client_id=" + App.appId + 
                "&redirect_uri=" + App.siteUrl +
                "&client_secret=" + App.appSecret + 
                "&code=" + validationCode;

        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setConnectTimeout(0);
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            int ch;
            StringBuffer buf = new StringBuffer();
            while ((ch = con.getInputStream().read()) != -1) {
                buf.append((char) ch);
            }
            log.info(buf.toString()); // access_token

            Me me = login(buf.substring("access_token=".length()));

            boolean isMobile = true;
            Writer writer = this.response.getWriter();
            if (isMobile) {
                // for mobile
                writer.write("");
                
            } else {
                // for web page
                writer.write("Top page: " + me.user.getUsername()); // TODO: HTML
            }
        }
        
        return null;
    }
}
