package com.tictok.Commons;

import java.util.List;

public class ListaDTOConCount<T> {

    private int pages;
    private List<T> objects;

    public ListaDTOConCount(int pages, List<T> objects) {
        this.pages = pages;
        this.objects = objects;
    }

    public ListaDTOConCount() {
    }
}
