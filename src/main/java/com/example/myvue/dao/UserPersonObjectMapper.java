package com.example.myvue.dao;

import com.example.myvue.model.UserPersonObject;
import com.example.myvue.myException.DataBaseException;

import java.util.Map;
public interface UserPersonObjectMapper {
    
    UserPersonObject selectAccordPhone(String phone) throws DataBaseException ;

    int insertSelective(UserPersonObject upo) throws DataBaseException;

    int updateStatus(Map condition) throws  DataBaseException;

    UserPersonObject selectAccordPhoneAndPassWord(Map condition);

    UserPersonObject selectByPrimaryKey(String userId);
}