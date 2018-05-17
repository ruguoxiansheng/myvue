package com.example.myvue.dao;

import com.example.myvue.model.InvalidCode;
import com.example.myvue.myException.DataBaseException;

import java.util.HashMap;
import java.util.Map;

public interface InvalidCodeMapper {


    int replace(InvalidCode record) throws DataBaseException;

    InvalidCode selectByPhone(Map phone)  throws DataBaseException;
}