package com.example.myvue.model;

import java.util.Date;

public class CompanyQuotePrice {
    private Long quoteId;

    private String companyName;

    private Double companyValue;

    private Long quotePriceId;

    private Date createTime;

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Double getCompanyValue() {
        return companyValue;
    }

    public void setCompanyValue(Double companyValue) {
        this.companyValue = companyValue;
    }

    public Long getQuotePriceId() {
        return quotePriceId;
    }

    public void setQuotePriceId(Long quotePriceId) {
        this.quotePriceId = quotePriceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}