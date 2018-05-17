package com.example.myvue.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class QuotePrice {
    private Long quotePriceId;

    private Long consumerId;

    private String projectNumber;

    public Long getQuotePriceId() {
        return quotePriceId;
    }

    public void setQuotePriceId(Long quotePriceId) {
        this.quotePriceId = quotePriceId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber == null ? null : projectNumber.trim();
    }


     private List<CompanyQuotePrice> companyQuotePriceList;

    public List<CompanyQuotePrice> getCompanyQuotePriceList() {
        return companyQuotePriceList;
    }

    public void setCompanyQuotePriceList(List<CompanyQuotePrice> companyQuotePriceList) {
        this.companyQuotePriceList = companyQuotePriceList;
    }

    public  QuotePrice generator(JSONObject reqObj){
        this.setConsumerId(reqObj.getLong("consumerId"));
        this.setProjectNumber(reqObj.getString("projectNumber"));
        this.setCompanyQuotePriceList(reqObj.getJSONArray("comitData").toJavaList(CompanyQuotePrice.class));
        return this;
    }}