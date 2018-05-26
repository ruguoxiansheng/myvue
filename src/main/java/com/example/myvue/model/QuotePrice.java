package com.example.myvue.model;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
public class QuotePrice {
    private Long quotePriceId;

    private String consumerId;

    private String projectNumber;

    private Integer k;

    private Integer k2;

    public Long getQuotePriceId() {
        return quotePriceId;
    }

    public void setQuotePriceId(Long quotePriceId) {
        this.quotePriceId = quotePriceId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber == null ? null : projectNumber.trim();
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getK2() {
        return k2;
    }

    public void setK2(Integer k2) {
        this.k2 = k2;
    }


     private List<CompanyQuotePrice> companyQuotePriceList;

    public List<CompanyQuotePrice> getCompanyQuotePriceList() {
        return companyQuotePriceList;
    }

    public void setCompanyQuotePriceList(List<CompanyQuotePrice> companyQuotePriceList) {
        this.companyQuotePriceList = companyQuotePriceList;
    }

    public  QuotePrice generator(JSONObject reqObj){
        this.setConsumerId(reqObj.getString("consumerId"));
        this.setProjectNumber(reqObj.getString("projectNumber"));
        this.setCompanyQuotePriceList(reqObj.getJSONArray("comitData").toJavaList(CompanyQuotePrice.class));
        this.setK(reqObj.getInteger("k"));
        this.setK2(reqObj.getInteger("k2"));
        return this;
    }
}