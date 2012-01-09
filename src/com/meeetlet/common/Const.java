package com.meeetlet.common;

import com.google.appengine.api.utils.SystemProperty;

public class Const {

    public static String topPageUrl = "http://meeetlet.appspot.com";
    static {
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) 
            topPageUrl = "http://localhost:8888";
    }

    
    final public static String charEncoding = "application/json; charset=utf-8";
}
