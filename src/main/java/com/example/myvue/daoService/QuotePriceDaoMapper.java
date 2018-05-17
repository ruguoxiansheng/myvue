package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class QuotePriceDaoMapper {

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();

    @Resource
    private QuotePriceMapper quotePriceMapper;

    @Resource
    private CompanyQuotePriceDaoMapper companyQuotePriceDaoMapper;

    /**
     * 用户输入的数据入库
     * @param quotePrice
     * @return List<CompanyQuotePrice>
     */
    public List<CompanyQuotePrice> insertSelective(QuotePrice quotePrice) throws McException {

        // 插入数据之后，返回id
        insertValue(quotePrice);
        List<CompanyQuotePrice> companyQuotePrices =quotePrice.getCompanyQuotePriceList();
        // 把id给对象
        for (int i = 0; i < companyQuotePrices.size();i++) {
            companyQuotePrices.get(i).setQuotePriceId(quotePrice.getQuotePriceId());
        }
        // 批量插入之后，返回id
        try {
            companyQuotePriceDaoMapper.insertBatch(companyQuotePrices);
        } catch (DataBaseException e) {
            throw new McException("操作失败！",e.getExceptionCode());
        }
        return companyQuotePrices;
    }

    private void insertValue(QuotePrice quotePrice) {
        quotePriceMapper.insertValue(quotePrice);
    }

    public QuotePrice query(Map queryCondition) throws DataBaseException {
        QuotePrice quotePrice = null;
        try {
            readLock.lock();
            quotePrice = quotePriceMapper.query(queryCondition);
        } catch (DataBaseException e) {
            throw new DataBaseException("查询quote_price异常！","DBE-5");
        }finally {
            readLock.unlock();
        }
        return quotePrice;
    }
}
