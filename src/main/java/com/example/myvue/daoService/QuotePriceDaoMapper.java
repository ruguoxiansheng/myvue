package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuotePriceDaoMapper {
    @Resource
    private QuotePriceMapper quotePriceMapper;

    @Resource
    private CompanyQuotePriceDaoMapper companyQuotePriceDaoMapper;

    public List<QuotePrice> insertSelective(QuotePrice quotePrice) {

        insertValue(quotePrice);
    List<CompanyQuotePrice> companyQuotePrices =quotePrice.getCompanyQuotePriceList();
        // 批量插入之后，返回id
        companyQuotePriceDaoMapper.insertBatch(companyQuotePrices);
        List<QuotePrice> quotePrices = new ArrayList<>(companyQuotePrices.size());
        // 把id给对象

        for (int i = 0; i < companyQuotePrices.size();i++) {
            quotePrice.setQuoteId(companyQuotePrices.get(i).getQuoteId());
            quotePrices.add(quotePrice);
        }

        return quotePrices;
    }

    private void insertValue(QuotePrice quotePrice) {
        quotePriceMapper.insertValue(quotePrice);
    }

    private void insertBatch(List<QuotePrice> quotePrices) {
        quotePriceMapper.insertBatch(quotePrices);
    }
}
