package com.example.myvue.daoService;

import com.example.myvue.dao.AccountMapper;
import com.example.myvue.model.Account;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDaoMapper {

    @Resource
    private AccountMapper accountMapper;
    public void insertSelective(Account account) throws DataBaseException {
        accountMapper.insertSelective(account);
    }
}
