package com.example.myvue.utils;

import java.util.UUID;

public class ToolsUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
