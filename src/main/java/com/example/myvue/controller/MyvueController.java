package com.example.myvue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myvue.model.NetPage;
import com.example.myvue.model.QuotePrice;
import com.example.myvue.model.Student;
import com.example.myvue.service.CalTenderService;
import org.springframework.util.CollectionUtils;
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
@RequestMapping(value = "/vue")
public class MyvueController {
    @Resource
    private CalTenderService calTenderService;
    /**
     * 页面查询
     *
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/option1", method = RequestMethod.POST, consumes = "application/json")
    public Object open(@RequestBody JSONObject reqObj) throws Exception {
        NetPage netPage = new NetPage();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i <10;i++) {
            String name = "张"+i;
            String address = "张家村"+i;
            students.add( new Student(name,i+1,i+15,address));
        }
        netPage.setPageData(students);
        netPage.setTotalSize(students.size());
//        throw new Exception("测试错误！");
        return netPage;
    }

    /**
     * 这个接口的作用是计算标底价格
     * @param comitObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/calTender", method = RequestMethod.POST, consumes = "application/json")
    public Object calTender(@RequestBody JSONObject comitObj) throws Exception {
        calTenderService.calCulateQuotePrice(comitObj);
        return null;
    }

    /**
     * 这个接口的作用是异步调用根据用户的id，项目编号，判断用户是否计算过
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/alreadyCalTender", method = RequestMethod.POST, consumes = "application/json")
    public Object alreadyCalTender(@RequestBody JSONObject reqObj) throws Exception {
        Map queryCondition = JSON.toJavaObject(reqObj,Map.class);
        if (CollectionUtils.isEmpty(queryCondition)) {
                throw new Exception("请传入查询条件");
        }
        // 根据项目编号查询项目是否存在，不存在就抛异常
        return calTenderService.queryAlreadyCalTender(queryCondition);
    }
}
