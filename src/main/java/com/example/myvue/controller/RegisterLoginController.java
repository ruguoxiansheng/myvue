package com.example.myvue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myvue.model.NetPage;
import com.example.myvue.model.Student;
import com.example.myvue.myException.McException;
import com.example.myvue.service.CalTenderService;
import com.example.myvue.service.RegisterLoginService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 注册，登陆控制层
 */
@RestController
@RequestMapping(value = "/vue")
public class RegisterLoginController {

    @Resource
    private RegisterLoginService registerLoginService;
    /**
     * 是否注册
     * @param phone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isRegister", method = RequestMethod.POST, consumes = "application/json")
    public Boolean isRegister(@RequestParam String phone) throws McException{
        return   registerLoginService.isRegister(phone);
    }

    /**
     * 是否登陆
     * @param phone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST, consumes = "application/json")
    public Boolean isLogin(@RequestParam String phone) throws McException{
        return   registerLoginService.isLogin(phone);
    }

    /**
     * 注册
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public Boolean register(@RequestBody JSONObject reqObj) throws McException {
        // 校验字段
        String phone = reqObj.getString("phone");

        if (registerLoginService.isRegister(phone)){
            return false;
        }
        // 如果用户不存在就注册用户，生成两张表user_person_object，account
        registerLoginService.register(phone);
        return true;
    }

    /**
     * 登陆
     * @param reqObj
     * @return 登陆成功之后，返回id
     * @throws McException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public String login(@RequestBody JSONObject reqObj) throws McException {
        // 校验字段
        String phone = reqObj.getString("phone");
        if (registerLoginService.isLogin(phone)){
          throw new McException("用户已经登陆！","SERE-1");
        }
        // 如果用户不存在就注册用户，生成两张表user_person_object，account
       return registerLoginService.login(phone);
    }
}
