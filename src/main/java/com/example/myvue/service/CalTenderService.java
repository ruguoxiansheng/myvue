package com.example.myvue.service;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.calinterface.CalProQuotePrice;
import com.example.myvue.calinterface.impl.CalProQuotePriceImp;
import com.example.myvue.daoService.ProQuotePriceDaoMapper;
import com.example.myvue.daoService.ProjectObjectDaoMapper;
import com.example.myvue.daoService.QuotePriceDaoMapper;
import com.example.myvue.daoService.ShouldQuotePriceDaoMapper;
import com.example.myvue.model.*;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private ApplicationContext applicationContext;

    @Resource
    private CalProQuotePriceImp calProQuotePriceImp;

    @Resource
    private ProjectObjectDaoMapper projectObjectDaoMapper;

    public String calCulateQuotePrice(JSONObject comitObj) throws McException, DataBaseException {
        // 字段校验
        // 把传输过来的数据全部入库，加事物,将数据的id返回
        QuotePrice quotePrice = new QuotePrice();
        quotePrice.generator(comitObj);
        // 根据编号验证是否计算过
        queryAlreadyCalTender(quotePrice);

        quotePriceDaoMapper.insertSelective(quotePrice);

        try {
        // 根据项目编号，查到项目对象
        ProjectObject projectObject =  projectObjectDaoMapper.queryProject(quotePrice.getProjectNumber());
        // 根据方法名，反射到算法
        CalProQuotePrice calProQuotePriceService = (CalProQuotePrice)applicationContext.getBean(projectObject.getMethodType());
        // 计算应中标底
        ShouldQuotePrice shouleQuotePrice = calProQuotePriceService.calShouldQuotePrice(quotePrice,projectObject);
        // 5.3、计算有效报价
        List<ProQuotePrice> proQuotePrices= calProQuotePriceService.calProQuotePrice(shouleQuotePrice,quotePrice);

        // 添加一个事物，将应中标底和前五的价格全部入库
         insertResultToData(shouleQuotePrice, proQuotePrices);
        } catch (Exception e) {
            throw new McException("可以到用户中心进行计算！","MCE-RC");
        }
        return "success";

    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = McException.class)
    public void insertResultToData(ShouldQuotePrice shouleQuotePrice, List<ProQuotePrice> proQuotePrices) throws DataBaseException {
                shouldQuotePriceDaoMapper.insert(shouleQuotePrice);
                proQuotePriceDaoMapper.insetBatch(proQuotePrices);
    }

        /**
         * 根据用户的id,项目编号，查询用户是否已经传输过了标底
         * @param queryCondition
         * @return true: 存在，false:不存在
         */
        public boolean queryAlreadyCalTender (QuotePrice queryCondition) throws DataBaseException, McException {
                QuotePrice quotePrice = quotePriceDaoMapper.query(queryCondition);
                if (quotePrice !=null) {
                   throw new McException("该项目您已经计算过了，可以去用户中心查看!","MCE-V");
                }
                return  false;
        }

    /**
     *根据开标的时间去查询当天的项目编号，如果没有携带开标时间，就默认是当天的。
     * @param reqObj
     */
    public  List<JSONObject> queryProject(JSONObject reqObj) throws DataBaseException {
        String opentTime = reqObj.getString("openTime");
        if (StringUtils.isEmpty(opentTime)) {
            reqObj.put("openTime",null);
        }
        Map<String,String> condition = JSONObject.toJavaObject(reqObj,Map.class);
       List<ProjectObject> projectObjects = projectObjectDaoMapper.queryProjectAccordDate(condition);
        List<JSONObject> projectList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(projectObjects)) {
        for (int i =0; i < projectObjects.size();i++) {
                JSONObject p = new JSONObject();
                p.put("key",projectObjects.get(i).getProjectNumber());
                p.put("value",projectObjects.get(i).getProjectName());
                projectList.add(p);
            }

        }
        return projectList;
    }
}
