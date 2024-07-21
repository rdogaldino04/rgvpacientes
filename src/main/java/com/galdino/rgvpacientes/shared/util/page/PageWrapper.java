package com.galdino.rgvpacientes.shared.util.page;

import java.util.List;

import org.springframework.data.domain.Page;

@Deprecated
public class PageWrapper<T> {

    private Page<T> page;

    public PageWrapper(Page<T> page) {
        this.page = page;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public boolean isEmpty() {
        return page.getContent().isEmpty();
    }

    public int getNumber() {
        return page.getNumber();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public int getSize() {
        return this.page.getSize();
    }

    public long getTotalElements() {
        return this.page.getTotalElements();
    }

}
