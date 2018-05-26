package com.example.myvue.model;

import java.util.Date;

public class CompanyQuotePrice implements  Comparable<CompanyQuotePrice>  {
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

    @Override
    public int compareTo(CompanyQuotePrice o) {
        if (this.getCompanyValue() - o.getCompanyValue() >= 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"quoteId\":")
                .append(quoteId);
        sb.append(",\"companyName\":\"")
                .append(companyName).append('\"');
        sb.append(",\"companyValue\":")
                .append(companyValue);
        sb.append(",\"quotePriceId\":")
                .append(quotePriceId);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}