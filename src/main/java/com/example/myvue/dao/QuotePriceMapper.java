package com.example.myvue.dao;

import com.example.myvue.model.QuotePrice;

import java.util.List;
import java.util.Map;

public interface QuotePriceMapper {
   
    void insertValue(QuotePrice quotePrice);

    QuotePrice query(Map queryCondition);
}