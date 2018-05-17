package com.example.myvue.daoService;

import com.example.myvue.dao.InvalidCodeMapper;
import com.example.myvue.model.InvalidCode;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvalidCodeDaoMapper {

    @Resource
    private InvalidCodeMapper invalidCodeMapper;

    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.REPEATABLE_READ, rollbackFor = DataBaseException.class)
    public void replace(InvalidCode invalidCode) throws DataBaseException {
        invalidCodeMapper.replace(invalidCode);
    }

    public String getValidCode(Map condition) throws DataBaseException {
        InvalidCode invalidCode = null;
        try {
            invalidCode = invalidCodeMapper.selectByPhone(condition);
            if (null == invalidCode) {
                throw new DataBaseException("根据手机号，没有查到数据","DBE-10");
            }
        } catch (DataBaseException e) {
            throw new DataBaseException("数据库操作异常！","DBE-10");
        }
        return invalidCode.getInvalidCode();
    }
}
