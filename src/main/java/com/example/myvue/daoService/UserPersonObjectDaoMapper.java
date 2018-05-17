package com.example.myvue.daoService;

import com.alibaba.fastjson.JSONObject;
import com.example.myvue.dao.UserPersonObjectMapper;
import com.example.myvue.model.Account;
import com.example.myvue.model.UserPersonObject;
import com.example.myvue.myException.DataBaseException;
import com.example.myvue.myException.McException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class UserPersonObjectDaoMapper {

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock readLock = rwl.readLock();
    private final Lock writeLock = rwl.writeLock();

    @Resource
    private UserPersonObjectMapper userPersonObjectMapper;

    @Resource
    private  AccountDaoMapper accountDaoMapper;

    public UserPersonObject selectAccordPhone(String phone) throws DataBaseException {
        UserPersonObject upo = null;
        try {
            readLock.lock();
            upo = userPersonObjectMapper.selectAccordPhone(phone);
        } catch (DataBaseException e) {
            throw  new DataBaseException("查询异常！","DBE-2");
        }finally {
            readLock.unlock();
        }
        return upo;
    }

    // 这里还要加锁,加事物，账务表和个人表都创建成功了，才算是成功
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.REPEATABLE_READ, rollbackFor = DataBaseException.class)
    public void register(UserPersonObject upo) throws DataBaseException {

        try {
            writeLock.lock();
            UserPersonObject upoRecord = userPersonObjectMapper.selectAccordPhone(upo.getPhone());
            if (upoRecord !=null){
                throw new DataBaseException("用户已经注册！","DBE-4");
            }
            insertSelective(upo);
        }finally {
            writeLock.unlock();
        }
    }

    private void insertSelective(UserPersonObject upo) throws DataBaseException {
        Account account = new Account();
        account.setAccountId(upo.getPersonAccountId());
        try {
            accountDaoMapper.insertSelective(account);
            userPersonObjectMapper.insertSelective(upo);
        } catch (DataBaseException e) {
           throw new DataBaseException("数据库插入异常！","DBE-2");
        }

    }

    /**
     * 登陆陈工之后，反回用户的
     * @param reqObj
     * @return String 用户的id
     * @throws McException 已经登陆过
     * @throws DataBaseException 数据库异常
     */
    public String login(JSONObject reqObj) throws McException, DataBaseException {

        // 这边要加锁,查询更改一起操作
        try {
            writeLock.lock();
            UserPersonObject upo = selectAccordPhone(reqObj.getString("phone"));
            if (null == upo) {
                throw new McException("用户未注册，请注册！","SERE-2");
            }
            if (upo.getStatus() ==1) {
                // 登陆了就抛异常
                throw new McException("用户已经登陆！","SERE-2");
            }
            // 没有登陆过就要更改status的状态为1
            Map condition = new HashMap();
            condition.put("phone",reqObj.getString("phone"));
            condition.put("passWord",reqObj.getString("passWord"));
            // 验证手机号或者密码是否正确
            UserPersonObject record = userPersonObjectMapper.selectAccordPhoneAndPassWord(condition);
            if (null ==record) {
                throw new McException("手机号或者密码错误！","MCE-3");
            }
            condition.put("status",1);
            userPersonObjectMapper.updateStatus(condition);
            return upo.getUserId();
        } catch (DataBaseException e) {
            throw new DataBaseException("数据库异常","DBE-3");
        }finally {
            writeLock.unlock();
        }
    }
}
