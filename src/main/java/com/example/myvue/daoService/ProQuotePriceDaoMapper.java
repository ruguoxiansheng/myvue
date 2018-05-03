package com.example.myvue.daoService;

import com.example.myvue.dao.ProQuotePriceMapper;
import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProQuotePriceDaoMapper {
    @Resource
    private ProQuotePriceMapper proquotePriceMapper;

    public List<QuotePrice> insertSelective(ProQuotePrice proQuotePrice) {
        proquotePriceMapper.insertSelective(proQuotePrice);
        return null;
    }
}
