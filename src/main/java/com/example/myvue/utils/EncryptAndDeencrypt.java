package com.example.myvue.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptAndDeencrypt {
    public static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    };

    public static String doEncrypt(JSONObject reqObj, String[] params, String salt) {
        StringBuffer sb = new StringBuffer(salt);
        String encdeStr = "";
        for (int i =0;i < params.length;i++) {
            sb.append(reqObj.get(params[i]));
        }
        try {
            byte[] hash  = messageDigest.digest(sb.toString().getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (UnsupportedEncodingException e) {
//            logger.error("fail to do shs256 digest, content:" + ticket,e);
        }
        return encdeStr;
    }
}
