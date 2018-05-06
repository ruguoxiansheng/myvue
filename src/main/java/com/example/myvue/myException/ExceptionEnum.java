package com.example.myvue.myException;

/**
 * Created by Administrator on 2018/2/6.
 */
public enum ExceptionEnum {

    UNKNOW_ERROR("UNE","未知错误"),
    SYSTEM_ERROR("SYSE","系统异常"),
    ;

    private String code;

    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
