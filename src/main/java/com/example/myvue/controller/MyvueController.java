package com.example.myvue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myvue.daoService.HistoryQueryService;
import com.example.myvue.model.NetPage;
import com.example.myvue.model.ProQuotePrice;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.Student;
import com.example.myvue.myannotation.LoginValid;
import com.example.myvue.service.CalTenderService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 */
@RestController
@RequestMapping(value = "/app")
@LoginValid
public class MyvueController {
    @Resource
    private CalTenderService calTenderService;

    @Resource
    private HistoryQueryService historyQueryService;


    /**
     * 这个接口的作用是计算标底价格
     * @param comitObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/calTender", method = RequestMethod.POST, consumes = "application/json")
    public Object calTender(@RequestBody JSONObject comitObj) throws Exception {
        String result = calTenderService.calCulateQuotePrice(comitObj);
        return result;
    }

    /**
     * 这个接口的作用是异步调用根据用户的id，项目编号，判断用户是否计算过
     * @param quotePrice
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alreadyCalTender", method = RequestMethod.POST, consumes = "application/json")
    public Object alreadyCalTender(@RequestBody QuotePrice quotePrice) throws Exception {
        // 根据项目编号查询项目是否存在，不存在就抛异常
        //校验项目编号是否合法
        return calTenderService.queryAlreadyCalTender(quotePrice);
    }


    /**
     * 这个接口的作用是根据开标的时间，查询到当前的开标项目
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryProject", method = RequestMethod.POST, consumes = "application/json")
    public Object alreadyCalTender(@RequestBody JSONObject reqObj) throws Exception {
        return calTenderService.queryProject(reqObj);
    }


    /**
     * 这个接口的作用是查询用户上传的历史记录
     * @param quotePrice
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryComitHistory", method = RequestMethod.POST, consumes = "application/json")
    public Object queryComitHistory(@RequestBody QuotePrice quotePrice) throws Exception {

     QuotePrice quotePriceRecord =   historyQueryService.queryComit(quotePrice);

     return quotePriceRecord;
    }
}
