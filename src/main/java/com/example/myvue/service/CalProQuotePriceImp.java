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
    public ShouldQuotePrice calShouldQuotePrice(QuotePrice quotePrice) {
        // 根据项目名称，反射调用到算法

        // 将公司的报价发送给算法，返回应中标价格
        return null;
    }
    public List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice  shouldQuotePrice,QuotePrice quotePrice) {
        return null;
    }
}
