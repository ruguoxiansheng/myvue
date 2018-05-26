package com.example.myvue.dao;

import com.example.myvue.model.ProjectObject;
import com.example.myvue.myException.DataBaseException;

import java.util.List;
import java.util.Map;

public interface ProjectObjectMapper {


    ProjectObject selectByPrimaryKey(String projectNumber);


    List<ProjectObject> queryProjectAccordDate(Map<String, String> condition);
}