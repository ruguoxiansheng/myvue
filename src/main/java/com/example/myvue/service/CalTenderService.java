package com.example.myvue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myvue.daoService.CompanyQuotePriceDaoMapper;
import com.example.myvue.daoService.ProQuotePriceDaoMapper;
import com.example.myvue.daoService.QuotePriceDaoMapper;
import com.example.myvue.daoService.ShouldQuotePriceDaoMapper;
import com.example.myvue.model.CompanyQuotePrice;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.ShouldQuotePrice;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
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

    public String calCulateQuotePrice(JSONObject comitObj){
        // 字段校验
        // 把传输过来的数据全部入库，加事物,将数据的id返回
       QuotePrice quotePrice = new QuotePrice();
        quotePrice.generator(comitObj);
        List<CompanyQuotePrice> comQuotePrices = quotePriceDaoMapper.insertSelective(quotePrice);

        // 计算应中标底
        ShouldQuotePrice shouleQuotePrice = calProQuotePriceImp.calShouldQuotePrice(quotePrice);

        //计算出前五的价格
        List<ProQuotePrice> proQuotePrices = calProQuotePriceImp.calProQuotePrice(shouleQuotePrice,quotePrice);

        // 计算结果不对时，抛出异常
        if (CollectionUtils.isEmpty(proQuotePrices)) {

        }
        // 添加一个事物，将应中标底和前五的价格全部入库
        insertResultToData(shouleQuotePrice,proQuotePrices);
        return "success";
    }

    private void insertResultToData(ShouldQuotePrice shouleQuotePrice, List<ProQuotePrice> proQuotePrices) {
        shouldQuotePriceDaoMapper.insert(shouleQuotePrice);
        proQuotePriceDaoMapper.insetBatch(proQuotePrices);
    }

    /**
     * 根据用户的id,项目编号，查询用户是否已经传输过了标底
     * @param queryCondition
     */
    public boolean queryAlreadyCalTender(Map queryCondition) {
        QuotePrice quotePrice = quotePriceDaoMapper.query(queryCondition);
        if (queryCondition != null) {
            return true;
        }
        return false;
    }
}
