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

    /**
     * 用户输入的数据入库
     * @param quotePrice
     * @return List<CompanyQuotePrice>
     */
    public List<CompanyQuotePrice> insertSelective(QuotePrice quotePrice) {

        // 插入数据之后，返回id
        insertValue(quotePrice);
        List<CompanyQuotePrice> companyQuotePrices =quotePrice.getCompanyQuotePriceList();
        // 把id给对象
        for (int i = 0; i < companyQuotePrices.size();i++) {
            companyQuotePrices.get(i).setQuotePriceId(quotePrice.getQuotePriceId());
        }
        // 批量插入之后，返回id
        companyQuotePriceDaoMapper.insertBatch(companyQuotePrices);
        return companyQuotePrices;
    }

    private void insertValue(QuotePrice quotePrice) {
        quotePriceMapper.insertValue(quotePrice);
    }
}
