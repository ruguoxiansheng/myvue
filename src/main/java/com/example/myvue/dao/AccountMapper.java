package com.example.myvue.dao;

import com.example.myvue.model.Account;
import com.example.myvue.myException.DataBaseException;

public interface AccountMapper {

    int insertSelective(Account record) throws DataBaseException;
}