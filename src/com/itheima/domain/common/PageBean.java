package com.itheima.domain.common;

import java.util.List;

public class PageBean<T> {
    private Long total;  //总记录数
    private List<T> list; //当前页数据

    private Integer currPage;  //当前页页码
    private Integer pageSize;  //每页条数
    private Integer pages; //总页数
    private Integer prePage; //上一页页码
    private Integer nextPage; //下一页页码
    private Integer navigateFirstPage; //分页条起始页码
    private Integer navigateLastPage; //分页条结束 页码

    public PageBean() {}

    public PageBean(Integer currPage, Integer pageSize, Long total, List<T> list) {
        this.currPage=currPage;
        this.pageSize=pageSize;
        this.total = total;
        this.list = list;

        //初始化其他属性
        this.pages = (int) Math.ceil(total * 1.0 / pageSize );
        this.prePage= Math.max(currPage - 1, 1);
        this.nextPage= Math.min(currPage + 1, this.pages);

        if(pages<=10){
            this.navigateFirstPage=1;
            this.navigateLastPage=this.pages;
        }else{
            //设置初始值
            this.navigateFirstPage=this.currPage-5;
            this.navigateLastPage=this.currPage+4;
            //当超出临界值时，需要调整
            if(this.navigateFirstPage<1){ //this.currPage-5有可能小于0,用户在浏览1,2,3,4,5页的情况
                this.navigateFirstPage=1;
                this.navigateLastPage=this.navigateFirstPage+9;
            }
            if(this.navigateLastPage>this.pages){ //this.currPage+4有可能大于总页数，用户在浏览倒数1,2,3,4页的情况
                this.navigateLastPage=this.pages;
                this.navigateFirstPage=this.navigateLastPage-9;
            }
        }
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(Integer navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public Integer getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(Integer navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "total=" + total +
                ", list=" + list +
                ", currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", navigateFirstPage=" + navigateFirstPage +
                ", navigateLastPage=" + navigateLastPage +
                '}';
    }
}
