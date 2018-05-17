package com.example.myvue.daoService;

import com.example.myvue.dao.CompanyQuotePriceMapper;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Service
public class CompanyQuotePriceDaoMapper {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();
    @Resource
    private CompanyQuotePriceMapper companyQuotePriceMapper;
    public void insertBatch(List<CompanyQuotePrice> companyQuotePrices) throws DataBaseException {
        try {
            writeLock.lock();
            companyQuotePriceMapper.insertBatch(companyQuotePrices);
        } catch (DataBaseException e) {
           throw new DataBaseException("批量插入company_quote_price失败！","DBE-6");
        }finally {
            writeLock.unlock();
        }
    }
}
