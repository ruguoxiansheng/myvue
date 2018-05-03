package com.example.myvue.dao;

import com.example.myvue.model.CompanyQuotePrice;

import java.util.List;

public interface CompanyQuotePriceMapper {
      void insertBatch(List<CompanyQuotePrice> companyQuotePrices);
}