package com.example.myvue.myException;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2018/2/2.
 */
public class McException extends Exception  {

    Logger log = Logger.getLogger(McException.class);

    private String exceptionCode;

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public McException() {
    }

    public McException(String message, String exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

}
