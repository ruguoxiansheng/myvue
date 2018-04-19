package com.example.myvue.model;

/**
 * Created by Administrator on 2018/4/19.
 */
public class FatherModel {
    // 分页查询，查询第几页
    public  int pageStart;
    // 分页查询，每页多少条
    public int pageSize;

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
