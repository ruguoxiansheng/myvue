package com.example.myvue.daoService;

import com.example.myvue.dao.ProjectObjectMapper;
import com.example.myvue.model.ProjectObject;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectObjectDaoMapper {

    @Resource
    private ProjectObjectMapper projectObjectMapper;

    public ProjectObject queryProject(String projectNum) throws DataBaseException {
        try {
            ProjectObject projectObject = projectObjectMapper.selectByPrimaryKey(projectNum);
         if (projectObject == null) {
             throw new DataBaseException("项目不存在！","DBE-9");
         }
         return projectObject;
        } catch (DataBaseException e) {
            throw new DataBaseException("数据库查询异常！","DBE-10");
        }
    }
}
