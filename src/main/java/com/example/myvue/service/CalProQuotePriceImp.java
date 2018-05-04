package com.example.myvue.service;

import com.example.myvue.calinterface.CalProQuotePrice;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalProQuotePriceImp implements CalProQuotePrice {
    @Override
    public ShouldQuotePrice calShouldQuotePrice(QuotePrice quotePrice) {
        // 根据项目名称，反射调用到算法

        // 将公司的报价发送给算法，返回应中标价格
      // 这里先把所有的数据求平均吧
        //  这里先假设返回前五个数值
        double sum = 0.00;
        List<CompanyQuotePrice> companyQuotePrices = quotePrice.getCompanyQuotePriceList();
        for (int i =0 ;i < companyQuotePrices.size();i++) {
            sum+=companyQuotePrices.get(i).getCompanyValue();
        }
        ShouldQuotePrice shouldQuotePrice = new ShouldQuotePrice();
        shouldQuotePrice.setProjectNumber(quotePrice.getProjectNumber());
        shouldQuotePrice.setShouldPrice(sum/companyQuotePrices.size());
        shouldQuotePrice.setConsumerId(quotePrice.getConsumerId());
        return shouldQuotePrice;
    }
    public List<ProQuotePrice> calProQuotePrice(ShouldQuotePrice  shouldQuotePrice,QuotePrice quotePrice) {
        // 先返回前五个数
        List<CompanyQuotePrice> companyQuotePrices = quotePrice.getCompanyQuotePriceList();
        List<ProQuotePrice> proQuotePrices = new ArrayList<>(5);
        for(int i=0;i<5;i++) {
            ProQuotePrice proQuotePrice = new ProQuotePrice();
            proQuotePrice.setProQuotePriceId(companyQuotePrices.get(i).getQuoteId());
            proQuotePrice.setQuotePriceId(quotePrice.getQuotePriceId());
            proQuotePrices.add(proQuotePrice);
        }
        return proQuotePrices;
    }
}
