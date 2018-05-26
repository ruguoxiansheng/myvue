package com.example.myvue.daoService;

import com.example.myvue.dao.QuotePriceMapper;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.REPEATABLE_READ, rollbackFor = DataBaseException.class)
    public List<CompanyQuotePrice> insertSelective(QuotePrice quotePrice) throws  DataBaseException {
        // 插入数据到qp，返回id
        insertValue(quotePrice);
        List<CompanyQuotePrice> companyQuotePrices =quotePrice.getCompanyQuotePriceList();
        // 把id给对象
        for (int i = 0; i < companyQuotePrices.size();i++) {
            companyQuotePrices.get(i).setQuotePriceId(quotePrice.getQuotePriceId());
        }
        companyQuotePriceDaoMapper.insertBatch(companyQuotePrices);
        return companyQuotePrices;
    }
/*
插入数据到Quote_price表中
 */
    private void insertValue(QuotePrice quotePrice) throws DataBaseException {
        try {
            quotePriceMapper.insertValue(quotePrice);
        }catch (Exception e) {
            throw new  DataBaseException("数据库插入异常！","DBE-C-QP");
        }
    }

    public QuotePrice query(QuotePrice queryCondition) throws DataBaseException {
        QuotePrice quotePrice = null;
        try {
            readLock.lock();
            quotePrice = quotePriceMapper.query(queryCondition);
        } catch (Exception e) {
            throw new DataBaseException("查询异常！","DBE-Q-QP");
        }finally {
            readLock.unlock();
        }
        return quotePrice;
    }

    public QuotePrice selectRecord(QuotePrice quotePrice) {
        QuotePrice quotePriceRecord =   quotePriceMapper.selectRecord(quotePrice);
        return quotePriceRecord;
    }
}
