package org.gridview.backend.domain;

import java.util.List;

public class Page<T> {
    private List<T> content;
    private final int number;
    private final boolean hasNext;
    private long totalPages;
    private int totalElements;

    public int getNumber() {
        return number;
    }

    public boolean isHasNext() {
        return hasNext;
    }



    public Page(List<T> content, int number, boolean b, long totalPage, int totalElements) {
        this.content = content;
        this.number = number;
        this.hasNext = b;
        this.totalPages = totalPage;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
