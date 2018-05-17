package com.example.myvue.service;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.daoService.InvalidCodeDaoMapper;
import com.example.myvue.model.InvalidCode;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import com.example.myvue.utils.ToolsUtil;
import com.example.myvue.daoService.UserPersonObjectDaoMapper;
import com.example.myvue.model.UserPersonObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RegisterLoginService {

    @Resource
    private UserPersonObjectDaoMapper userPersonObjectDaoMapper;

    @Resource
    private  InvalidCodeService invalidCodeService;

    @Resource
    private InvalidCodeDaoMapper invalidCodeDaoMapper;

    /**
     * 根据用户的手机号判断是否注册过
     * @param phone
     * @return true:注册过，false:没有注册过
     */
    public boolean isRegister(String phone) throws McException {
        UserPersonObject upo = null;
        try {
            upo = userPersonObjectDaoMapper.selectAccordPhone(phone);
            if (null != upo) {
                throw  new McException("用户已经注册过！","MCE-10");
            }
        } catch (DataBaseException e) {
            throw  new McException("查询异常！",e.getExceptionCode());
        }
        return upo !=null;
    }

    /**
     *根据用户的手机号，创建数据
     * @param reqObj
     * @return
     */
    public boolean register(JSONObject reqObj) throws McException {
        // 校验验证码
        String invalidCode = reqObj.getString("invalidCode");
        Map<String,String> condition = new HashMap<>();
        condition.put("phone",reqObj.getString("phone"));
        try {
        String invalidCode2 =    invalidCodeDaoMapper.getValidCode(condition);
        if (invalidCode2 ==null || !invalidCode2.equals(invalidCode)) {
            throw new McException("验证码校验不通过！","MCE-10");
        }
        } catch (DataBaseException e) {
            throw new McException("验证码校验不通过！","MCE-10");
        }

        // 根据用户的手机号，生成account_id，user_id
        UserPersonObject userPersonObject = new UserPersonObject();
        userPersonObject.setUserId(ToolsUtil.getUUID());
        userPersonObject.setPersonAccountId(ToolsUtil.getUUID());
        userPersonObject.setPhone(reqObj.getString("phone"));
        userPersonObject.setPassWord(reqObj.getString("passWord"));
        try {
            userPersonObjectDaoMapper.register(userPersonObject);
        } catch (DataBaseException e) {
          throw new McException(e.getMessage(),e.getExceptionCode());
        }
        return true;
    }

    /**
     * 判断用户是否登陆
     * @param phone
     * @return true:登陆，false:没有登陆
     */
    public Boolean isLogin(String phone) throws McException {
        try {
          UserPersonObject upo =  userPersonObjectDaoMapper.selectAccordPhone(phone);
          if (null == upo) {
              throw new McException("用户没有注册过，请注册！","MCE-3");
          }
          // 1，已经登陆，否则没有
            if( upo.getStatus() == 1) {
                throw new McException("用户已经登陆过！","MCE-3");
            }
            return true;
        } catch (DataBaseException e) {
            throw  new McException("查询异常！",e.getExceptionCode());
        }
    }

    public String login(JSONObject reqObj) throws McException {

        try {
          return  userPersonObjectDaoMapper.login(reqObj);
        } catch (DataBaseException e) {
          throw new McException("查询异常！",e.getExceptionCode());
        }
    }

    /**
     * 获取手机号的验证码
     * @param phone
     */
    public void invalid(String phone) throws McException {
        JSONObject params = InvalidCodeService.createRandomVcode();
        try {
//            invalidCodeService.msgSend(phone,"马鞍山计价","SMS_133979682",params.toJSONString());
            //验证码入库
            InvalidCode invalidCode = new InvalidCode();
            invalidCode.setPhone(phone);
            invalidCode.setInvalidCode(params.getString("code"));
            invalidCodeDaoMapper.replace(invalidCode);
        } catch (Exception e) {
            throw new McException("请重新获取验证码！","MCE-1");
        }

    }
}
