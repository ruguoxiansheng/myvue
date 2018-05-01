package com.example.myvue.daoService;

import com.example.myvue.dao.CompanyQuotePriceMapper;
import com.example.myvue.model.CompanyQuotePrice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CompanyQuotePriceDaoMapper {
    @Resource
    private CompanyQuotePriceMapper companyQuotePriceMapper;
    public void insertBatch(List<CompanyQuotePrice> companyQuotePrices) {
        companyQuotePriceMapper.insertBatch(companyQuotePrices);
    }
}
