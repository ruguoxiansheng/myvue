package com.example.myvue.daoService;

import com.example.myvue.model.QuotePrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HistoryQueryService {

    @Resource
    private  QuotePriceDaoMapper quotePriceDaoMapper;

    public QuotePrice queryComit(QuotePrice quotePrice) {

        QuotePrice quotePriceRecord = quotePriceDaoMapper.selectRecord(quotePrice);

        return quotePriceRecord;
    }
}
