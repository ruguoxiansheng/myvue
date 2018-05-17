package com.example.myvue.dao;

import com.example.myvue.model.ProjectObject;
import com.example.myvue.myException.DataBaseException;

public interface ProjectObjectMapper {
    int deleteByPrimaryKey(String projectNumber);

    int insert(ProjectObject record);

    int insertSelective(ProjectObject record);

    ProjectObject selectByPrimaryKey(String projectNumber) throws DataBaseException;

    int updateByPrimaryKeySelective(ProjectObject record);

    int updateByPrimaryKey(ProjectObject record);
}