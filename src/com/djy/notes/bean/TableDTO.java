package com.djy.notes.bean;

import java.util.Vector;

/**
 * 封装表格返回数据
 */
public class TableDTO {
    //多行数据
    private Vector<Vector<Object>> data;
    //总共行数
    private int totalCount;

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}

