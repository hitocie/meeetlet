package com.meeetlet.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String toString(Date date) {
        if (date == null)
            return null;
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }
}
