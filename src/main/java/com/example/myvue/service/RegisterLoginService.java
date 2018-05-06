package com.example.myvue.service;

import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import com.example.myvue.utils.ToolsUtil;
import com.example.myvue.daoService.UserPersonObjectDaoMapper;
import com.example.myvue.model.UserPersonObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterLoginService {

    @Resource
    private UserPersonObjectDaoMapper userPersonObjectDaoMapper;

    /**
     * 根据用户的手机号判断是否注册过
     * @param phone
     * @return true:注册过，false:没有注册过
     */
    public boolean isRegister(String phone) throws McException {
        UserPersonObject upo = null;
        try {
            upo = userPersonObjectDaoMapper.selectAccordPhone(phone);
        } catch (DataBaseException e) {
            throw  new McException("查询异常！",e.getExceptionCode());
        }
        return upo !=null;
    }

    /**
     *根据用户的手机号，创建数据
     * @param phone
     * @return
     */
    public boolean register(String phone) throws McException {
        // 根据用户的手机号，生成account_id，user_id
        // 这里要添加一个事物控制
        UserPersonObject userPersonObject = new UserPersonObject();
        userPersonObject.setUserId(ToolsUtil.getUUID());
        userPersonObject.setPersonAccountId(ToolsUtil.getUUID());
        userPersonObject.setPhone(phone);
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
          // 1，已经登陆，否则没有
            return upo.getStatus() == 1;
        } catch (DataBaseException e) {
            throw  new McException("查询异常！",e.getExceptionCode());
        }
    }

    public String login(String phone) throws McException {

        try {
          return  userPersonObjectDaoMapper.login(phone);
        } catch (DataBaseException e) {
          throw new McException("查询异常！",e.getExceptionCode());
        }
    }
}
