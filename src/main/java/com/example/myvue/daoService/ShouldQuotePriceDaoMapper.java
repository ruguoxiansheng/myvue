package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.dao.ShouldQuotePriceMapper;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShouldQuotePriceDaoMapper {
    @Resource
    private ShouldQuotePriceMapper shouldQuotePriceMapper;

    public void insert(ShouldQuotePrice shouleQuotePrice) throws DataBaseException {
        try {
            shouldQuotePriceMapper.insert(shouleQuotePrice);
        } catch (Exception e) {
            throw new DataBaseException("插入should_quote_price异常!","DBE-7");
        }
    }
}
