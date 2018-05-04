package com.example.myvue.dao;

import com.example.myvue.model.ProQuotePrice;
import java.util.List;
public interface ProQuotePriceMapper {
      void insertBatch(List<ProQuotePrice> proQuotePrices);
}