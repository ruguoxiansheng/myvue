package com.example.myvue.daoService;

import com.example.myvue.dao.ProjectObjectMapper;
import com.example.myvue.model.ProjectObject;
import com.example.myvue.myException.DataBaseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectObjectDaoMapper {

    @Resource
    private ProjectObjectMapper projectObjectMapper;

    public ProjectObject queryProject(String projectNum) throws DataBaseException {
        try {
            ProjectObject projectObject = projectObjectMapper.selectByPrimaryKey(projectNum);
         if (projectObject == null) {
             throw new DataBaseException("项目不存在！","DBE-R-PO");
         }
         return projectObject;
        } catch (Exception e) {
            throw new DataBaseException("数据库查询异常！","DBE-PO");
        }
    }

    public List<ProjectObject> queryProjectAccordDate(Map<String, String> condition) throws DataBaseException {
        List<ProjectObject> projectObjects = new ArrayList<>();
        try {
            projectObjects = projectObjectMapper.queryProjectAccordDate(condition);
        }catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("数据库查询异常！","DBE-PO");
        }
        return projectObjects;
    }
}
