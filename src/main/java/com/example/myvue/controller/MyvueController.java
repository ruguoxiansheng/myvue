package com.example.myvue.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.model.NetPage;
import com.example.myvue.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
@RestController
@RequestMapping(value = "/vue")
public class MyvueController {
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

    @RequestMapping(value = "/calTender", method = RequestMethod.POST, consumes = "application/json")
    public Object calTender(@RequestBody JSONObject reqObj) throws Exception {
        // 计算好标的价格

        // 计算出前五名的价格，将数据存储到数据库

        return null;
    }
}
