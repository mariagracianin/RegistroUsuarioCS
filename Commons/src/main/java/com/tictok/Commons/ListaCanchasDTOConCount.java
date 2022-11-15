package com.tictok.Commons;

import java.util.List;

public class ListaCanchasDTOConCount {

    private int pages;
    private List<SuperCanchaDTO> objects;

    public ListaCanchasDTOConCount(int pages, List<SuperCanchaDTO> objects) {
        this.pages = pages;
        this.objects = objects;
    }

    public ListaCanchasDTOConCount() {
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<SuperCanchaDTO> getObjects() {
        return objects;
    }

    public void setObjects(List<SuperCanchaDTO> objects) {
        this.objects = objects;
    }
}
