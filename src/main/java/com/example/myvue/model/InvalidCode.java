package com.example.myvue.model;

public class InvalidCode {
    private String phone;

    private String invalidCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getInvalidCode() {
        return invalidCode;
    }

    public void setInvalidCode(String invalidCode) {
        this.invalidCode = invalidCode == null ? null : invalidCode.trim();
    }
}