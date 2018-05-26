package com.example.myvue.myException;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DataBaseException extends SQLException {

    Logger log = Logger.getLogger(DataBaseException.class);

    private String exceptionCode;

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public DataBaseException() {
    }

    public DataBaseException(String displayMessage, String exceptionCode) {
        super(displayMessage);
        this.exceptionCode = exceptionCode;
    }
}
