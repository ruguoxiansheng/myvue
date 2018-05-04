package com.example.myvue.model;

public class ProQuotePrice {
    private Long id;

    private Long proQuotePriceId;

    private Long quotePriceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProQuotePriceId() {
        return proQuotePriceId;
    }

    public void setProQuotePriceId(Long proQuotePriceId) {
        this.proQuotePriceId = proQuotePriceId;
    }

    public Long getQuotePriceId() {
        return quotePriceId;
    }

    public void setQuotePriceId(Long quotePriceId) {
        this.quotePriceId = quotePriceId;
    }
}