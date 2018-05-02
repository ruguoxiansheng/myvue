package com.example.myvue.dao;

import com.example.myvue.model.QuotePrice;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuotePriceMapper {


    void insertBatch(List<QuotePrice> quotePrices);

    void insertValue(QuotePrice quotePrice);
}