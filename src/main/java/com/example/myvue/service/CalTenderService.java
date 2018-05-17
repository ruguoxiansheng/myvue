package com.example.myvue.service;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.dao.ProjectObjectMapper;
import com.example.myvue.daoService.ProQuotePriceDaoMapper;
import com.example.myvue.daoService.ProjectObjectDaoMapper;
import com.example.myvue.daoService.QuotePriceDaoMapper;
import com.example.myvue.daoService.ShouldQuotePriceDaoMapper;
import com.example.myvue.model.*;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 这个服务用户计算标的价格
 */
@Service
public class CalTenderService {
    @Resource
    private QuotePriceDaoMapper quotePriceDaoMapper;

    @Resource
    private ShouldQuotePriceDaoMapper shouldQuotePriceDaoMapper;

    @Resource
    private ProQuotePriceDaoMapper proQuotePriceDaoMapper;


    @Resource
    private CalProQuotePriceImp calProQuotePriceImp;

    @Resource
    private ProjectObjectDaoMapper projectObjectDaoMapper;

    public String calCulateQuotePrice(JSONObject comitObj) throws McException {
        // 字段校验
        // 把传输过来的数据全部入库，加事物,将数据的id返回
        QuotePrice quotePrice = new QuotePrice();
        quotePrice.generator(comitObj);

        try {
            List<CompanyQuotePrice> comQuotePrices = quotePriceDaoMapper.insertSelective(quotePrice);
        } catch (McException e) {
            // 批量插入失败了，批示用户再计算
            e.printStackTrace();
        }
        // 根据项目编号，查到项目对象
        try {
           ProjectObject projectObject =  projectObjectDaoMapper.queryProject(comitObj.getString("projectNumber"));
        } catch (DataBaseException e) {
            throw new McException(e.getMessage(),"MCE-9");
        }

        // 计算应中标底
        ShouldQuotePrice shouleQuotePrice = calProQuotePriceImp.calShouldQuotePrice(quotePrice,projectObject);

        //计算出前五的价格
        List<ProQuotePrice> proQuotePrices = calProQuotePriceImp.calProQuotePrice(shouleQuotePrice, quotePrice);

        // 计算结果不对时，抛出异常
        if (CollectionUtils.isEmpty(proQuotePrices)) {

        }
        // 添加一个事物，将应中标底和前五的价格全部入库
        try {
            insertResultToData(shouleQuotePrice, proQuotePrices);
        } catch (McException e) {
            e.printStackTrace();
            // 这一步可以让用户自己去再计算！
            return "false";
        }
        return "success";
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = McException.class)
    public void insertResultToData(ShouldQuotePrice shouleQuotePrice, List<ProQuotePrice> proQuotePrices) throws McException {
            try {
                shouldQuotePriceDaoMapper.insert(shouleQuotePrice);
                proQuotePriceDaoMapper.insetBatch(proQuotePrices);
            } catch (DataBaseException e) {
                throw new McException("计算之后操作异常！", e.getExceptionCode());
            }
    }

        /**
         * 根据用户的id,项目编号，查询用户是否已经传输过了标底
         * @param queryCondition
         */
        public boolean queryAlreadyCalTender (Map queryCondition) throws McException {
            try {
                QuotePrice quotePrice = quotePriceDaoMapper.query(queryCondition);
                if (quotePrice !=null) {
                    return true;
                }
                return  false;
            } catch (DataBaseException e) {
                throw new McException("查询异常！",e.getExceptionCode());
            }
        }
}
