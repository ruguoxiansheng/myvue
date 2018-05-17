package com.example.myvue.model;

import java.util.Date;

public class ProjectObject {
    private String projectNumber;

    private String projectName;

    private Integer projectType;

    private Double budget;

    private Date openTime;

    private Long tenderId;

    private String methodType;

    private Float ratioPrice;

    private Float alertRatioPrice;

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber == null ? null : projectNumber.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Long getTenderId() {
        return tenderId;
    }

    public void setTenderId(Long tenderId) {
        this.tenderId = tenderId;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType == null ? null : methodType.trim();
    }

    public Float getRatioPrice() {
        return ratioPrice;
    }

    public void setRatioPrice(Float ratioPrice) {
        this.ratioPrice = ratioPrice;
    }

    public Float getAlertRatioPrice() {
        return alertRatioPrice;
    }

    public void setAlertRatioPrice(Float alertRatioPrice) {
        this.alertRatioPrice = alertRatioPrice;
    }
}