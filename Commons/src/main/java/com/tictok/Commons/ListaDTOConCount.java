package com.tictok.Commons;

import java.util.List;

public class ListaDTOConCount {

    private int pages;
    private List<SuperActividadDTO> objects;

    public ListaDTOConCount(int pages, List<SuperActividadDTO> objects) {
        this.pages = pages;
        this.objects = objects;
    }

    public ListaDTOConCount() {
    }

    public int getPages() {
        return pages;
    }

    public List<SuperActividadDTO> getObjects() {
        return objects;
    }
}
