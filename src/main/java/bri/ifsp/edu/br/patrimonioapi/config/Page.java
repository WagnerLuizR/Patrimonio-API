package bri.ifsp.edu.br.patrimonioapi.config;

import java.util.List;

public class Page<T> {

    private int page = 0;
    private int totalPage = 0;
    private List<T> content = null;
    private int totalRecords = 0;
    private int pageSize = 0;

    public Page() {
    }

    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public List<T> getContent() {
        return content;
    }


    public void setContent(List<T> content) {
        this.content = content;
    }


    public int getTotalRecords() {
        return totalRecords;
    }


    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }


    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
