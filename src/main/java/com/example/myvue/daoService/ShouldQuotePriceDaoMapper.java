package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.QuotePrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShouldQuotePriceDaoMapper {
    @Resource
    private QuotePriceMapper quotePriceMapper;

    public List<QuotePrice> insertSelective(QuotePrice quotePrice) {
        quotePriceMapper.insertSelective(quotePrice);
        return null;
    }
}
