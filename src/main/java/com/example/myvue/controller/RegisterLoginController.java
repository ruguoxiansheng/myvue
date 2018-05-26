package com.example.myvue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myvue.daoService.InvalidCodeDaoMapper;
import com.example.myvue.model.NetPage;
import com.example.myvue.model.Student;
import com.example.myvue.myException.McException;
import com.example.myvue.myannotation.ParamsValid;
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
@RequestMapping(value = "/login")
public class RegisterLoginController {

    @Resource
    private RegisterLoginService registerLoginService;

    /**
     * 是否注册
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isRegister", method = RequestMethod.POST, consumes = "application/json")
    public Object isRegister(@RequestBody JSONObject reqObj) throws McException{
        String phone = reqObj.getString("phone");
        return   registerLoginService.isRegister(phone);
    }

    /**
     * 是否登陆
     * @param reqObj
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST, consumes = "application/json")
    public Object isLogin(@RequestBody  JSONObject reqObj) throws McException{
        String phone = reqObj.getString("phone");
        return   registerLoginService.isLogin(phone);
    }

    /**
     * 注册
     * @param reqObj
     * @return
     * @throws Exception
     */
    @ParamsValid(values="invalidCode,phone,passWord")
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public Object register(@RequestBody JSONObject reqObj) throws McException {
        registerLoginService.register(reqObj);
        return true;
    }

    /**
     * 登陆
     * @param reqObj
     * @return 登陆成功之后，返回id
     * @throws McException
     */
    @ParamsValid(values = "phone,passWord")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public Object login(@RequestBody JSONObject reqObj) throws McException {
       return registerLoginService.login(reqObj);
    }

    /**
     * 验证码
     * @param reqObj
     * @return
     * @throws McException
     */
    @RequestMapping(value = "/invalidCode", method = RequestMethod.POST, consumes = "application/json")
    public Object invalidCode(@RequestBody JSONObject reqObj) throws McException {
        // 校验字段
        String phone = reqObj.getString("phone");

        registerLoginService.invalid(phone);

        return "获取验证码成功！";
    }
}
