package com.example.myvue.dao;

import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.myException.DataBaseException;
import sun.security.pkcs11.Secmod;

import java.util.List;
public interface ProQuotePriceMapper {
      void insertBatch(List<ProQuotePrice> proQuotePrices);
}