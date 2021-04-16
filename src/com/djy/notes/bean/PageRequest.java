package com.djy.notes.bean;

/**
 * @author djy
 */
public class PageRequest {
    // 当前表格的数据展示的是第几页
    private int pageNow =1;
    // 每一页有多少行
    private int pageSize =10;

    private int start;
    /**
     * 用户输入的搜索词
     */
    private String searchWord;

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public int getStart() {
        return (pageNow -1) * pageSize;
    }       //第一页：（1-1）*10=0 ——> 从第0条开始

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}