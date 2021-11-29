package com.chen.blog.vo;

/**
 * @ClassName PageResult
 * @Author ChenYicheng
 * @Description 分页处理类
 * @Date 2021/11/29 16:32
 */
public class PageResult {

    private int PageNumber;

    private Boolean flag;

    public PageResult() {
    }

    public PageResult(int pageNumber, Boolean flag) {
        PageNumber = pageNumber;
        this.flag = flag;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
