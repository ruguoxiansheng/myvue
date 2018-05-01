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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    public void calCulateQuotePrice(JSONObject comitObj){
        // 字段校验

        // 把传输过来的数据全部入库，加事物,将数据的id返回
       QuotePrice quotePrice = new QuotePrice();
        quotePrice.generator(comitObj);
        List<QuotePrice> quotePrices = quotePriceDaoMapper.insertSelective(quotePrice);

        // 计算应中标底
        ShouldQuotePrice shouleQuotePrice = calProQuotePriceImp.calShouldQuotePrice(quotePrices);
        //计算出前五的价格
        List<ProQuotePrice> proQuotePrices = calProQuotePriceImp.calProQuotePrice(shouleQuotePrice);

        // 添加一个事物，将应中标底和前五的价格全部入库

    }

}
