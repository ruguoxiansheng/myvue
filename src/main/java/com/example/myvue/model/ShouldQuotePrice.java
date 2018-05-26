package com.example.myvue.model;

import java.util.Date;

public class ShouldQuotePrice {
    private Long shoulePriceId;

    private Double shouldPrice;

    private String projectNumber;

    private String consumerId;

    private Date createTime;

    public Long getShoulePriceId() {
        return shoulePriceId;
    }

    public void setShoulePriceId(Long shoulePriceId) {
        this.shoulePriceId = shoulePriceId;
    }

    public Double getShouldPrice() {
        return shouldPrice;
    }

    public void setShouldPrice(Double shouldPrice) {
        this.shouldPrice = shouldPrice;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber == null ? null : projectNumber.trim();
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}