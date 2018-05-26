package com.example.myvue.daoService;

import com.example.myvue.dao.ProQuotePriceMapper;
import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProQuotePriceDaoMapper {
    @Resource
    private ProQuotePriceMapper proquotePriceMapper;

    public void insetBatch(List<ProQuotePrice> proQuotePrices) throws DataBaseException {
        try {
            proquotePriceMapper.insertBatch(proQuotePrices);
        } catch (Exception e) {
            throw new DataBaseException("批量插入pro_quote_price表失败！","DBE-I-PQP");
        }
    }
}
