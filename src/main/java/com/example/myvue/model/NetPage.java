package com.example.myvue.model;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */
public class NetPage {
    public int totalSize;
    public List<? extends  FatherModel> pageData;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<? extends FatherModel> getPageData() {
        return pageData;
    }

    public void setPageData(List<? extends FatherModel> pageData) {
        this.pageData = pageData;
    }
}
