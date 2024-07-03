package org.gridview.backend.domain;

import java.util.List;

public class Page<T> {
    private List<T> content;
    private final int number;

    public int getNumber() {
        return number;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    private final boolean hasNext;
    private long totalPages;

    public Page(List<T> content, int number, boolean b, long totalPage) {
        this.content = content;
        this.number = number;
        this.hasNext = b;
        this.totalPages = totalPage;
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
}
