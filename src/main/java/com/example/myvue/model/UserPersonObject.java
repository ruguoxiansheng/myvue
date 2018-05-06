package com.example.myvue.model;

public class UserPersonObject {
    private String userId;

    private String phone;

    private String userName;

    private String personAccountId;

    private Integer authoryType;

    private String idCardNum;

    private Integer status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPersonAccountId() {
        return personAccountId;
    }

    public void setPersonAccountId(String personAccountId) {
        this.personAccountId = personAccountId == null ? null : personAccountId.trim();
    }

    public Integer getAuthoryType() {
        return authoryType;
    }

    public void setAuthoryType(Integer authoryType) {
        this.authoryType = authoryType;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum == null ? null : idCardNum.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}