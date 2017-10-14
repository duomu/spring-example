package com.yll.springmvc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * Author：linlin.yang
 * Date：2017/5/17 17:25
 */
public class PageVo<E> implements Serializable {
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 分页查询的结果集
     */
    private List<E> voList;

    public PageVo(){
        this.currentPage=1;
        this.pageCount=-1;
        this.pageSize=10;
        this.setVoList(new ArrayList<E>());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getVoList() {
        return voList;
    }

    public void setVoList(List<E> voList) {
        this.voList = voList;
    }
}
