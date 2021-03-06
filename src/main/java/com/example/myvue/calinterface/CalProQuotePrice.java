package com.example.myvue.calinterface;

import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.ProjectObject;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;

import java.util.List;

public interface CalProQuotePrice {

    public ShouldQuotePrice calShouldQuotePrice(QuotePrice quotePrices, ProjectObject projectObject);

    public   List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice shouldQuotePrice, QuotePrice quotePrice);
}
