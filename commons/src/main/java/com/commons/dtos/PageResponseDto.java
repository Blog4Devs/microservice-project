package com.commons.dtos;
import java.util.List;


public class PageResponseDto<T> {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private long totalElement;
    private List<T> content;

    public PageResponseDto() {
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalElement() {
        return totalElement;
    }
    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }
    public List<T> getContent() {
        return content;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageResponseDto [currentPage=" + currentPage + ", totalPages=" + totalPages + ", pageSize=" + pageSize
                + ", totalElement=" + totalElement + ", content=" + content + "]";
    }

}