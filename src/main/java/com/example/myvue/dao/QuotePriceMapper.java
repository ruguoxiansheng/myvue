package com.example.myvue.dao;

import com.example.myvue.model.QuotePrice;
import com.example.myvue.myException.DataBaseException;

import java.util.Map;

public interface QuotePriceMapper {
   
    void insertValue(QuotePrice quotePrice)  throws DataBaseException;

    QuotePrice query(QuotePrice queryCondition);

    QuotePrice selectRecord(QuotePrice quotePrice);
}