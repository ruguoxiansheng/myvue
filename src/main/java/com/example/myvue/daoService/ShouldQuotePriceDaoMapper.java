package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.dao.ShouldQuotePriceMapper;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShouldQuotePriceDaoMapper {
    @Resource
    private ShouldQuotePriceMapper shouldQuotePriceMapper;

    public List<QuotePrice> insertSelective(ShouldQuotePrice shouldQuotePrice) {
        shouldQuotePriceMapper.insertSelective(shouldQuotePrice);
        return null;
    }
}
