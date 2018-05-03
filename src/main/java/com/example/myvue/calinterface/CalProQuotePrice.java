package com.example.myvue.calinterface;

import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;

import java.util.List;

public interface CalProQuotePrice {

    public ShouldQuotePrice calShouldQuotePrice(QuotePrice quotePrices);
}
