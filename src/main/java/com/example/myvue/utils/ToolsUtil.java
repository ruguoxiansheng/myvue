package com.example.myvue.utils;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

public class ToolsUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public  static String getDate(Date date, String format) {
        if (date == null) {
            date = new Date();
        }
        if (StringUtils.isEmpty(format)) {
            format="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);

    }

}
