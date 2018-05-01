package com.example.myvue.service;

import com.example.myvue.calinterface.CalProQuotePrice;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalProQuotePriceImp implements CalProQuotePrice {
    @Override
    public ShouldQuotePrice calShouldQuotePrice(List<QuotePrice> quotePrices) {
        return null;
    }
    public List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice  shouldQuotePrice) {
        return null;
    }
}
