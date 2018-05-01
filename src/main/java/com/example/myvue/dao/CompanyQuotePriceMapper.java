package com.example.myvue.dao;

import com.example.myvue.model.CompanyQuotePrice;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyQuotePriceMapper {

    void insertBatch(List<CompanyQuotePrice> companyQuotePrices);
}