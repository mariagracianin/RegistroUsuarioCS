package com.tictok.Commons;

import java.util.List;

public class ListaActividadesDTOConCount {

    private int pages;
    private List<SuperActividadDTO> objects;

    public ListaActividadesDTOConCount(int pages, List<SuperActividadDTO> objects) {
        this.pages = pages;
        this.objects = objects;
    }

    public ListaActividadesDTOConCount() {
    }

    public int getPages() {
        return pages;
    }

    public List<SuperActividadDTO> getObjects() {
        return objects;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setObjects(List<SuperActividadDTO> objects) {
        this.objects = objects;
    }
}
