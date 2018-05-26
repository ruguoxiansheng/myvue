package com.example.myvue.service;

import com.example.myvue.daoService.UserPersonObjectDaoMapper;
import com.example.myvue.model.UserPersonObject;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 用于自定义注解的实现
 */
@Service
public class ValidationService {

    @Resource
    private UserPersonObjectDaoMapper userPersonObjectDaoMapper;

    /**
     * 验证用户是否登陆
     * @param userId 用户的userId
     * @throws McException
     * @throws DataBaseException 数据库查询异常
     */
    public void loginValid(String userId) throws McException, DataBaseException {
        if (StringUtils.isEmpty(userId)) {
            throw new McException("请登陆！","MCE-LOGIN");
        }
        UserPersonObject upo = userPersonObjectDaoMapper.selectAccordUserId(userId);
        if (upo ==null || upo.getStatus() !=1) {
            throw new McException("请登陆！","MCE-LOGIN");
        }
    }
}
