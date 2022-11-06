package com.tictok.Commons;

public class ServicioResumenDTO {
    private String nombreServicio;
    private String tipoDeActividad;
    private int cantidadCheckIns;
    private Double importeTotal;


    public ServicioResumenDTO() {
    }

    public ServicioResumenDTO(String nombreServicio, String tipoDeActividad, int cantidadCheckIns, Double importeTotal) {
        this.nombreServicio = nombreServicio;
        this.tipoDeActividad = tipoDeActividad;
        this.cantidadCheckIns = cantidadCheckIns;
        this.importeTotal = importeTotal;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getTipoDeActividad() {
        return tipoDeActividad;
    }

    public void setTipoDeActividad(String tipoDeActividad) {
        this.tipoDeActividad = tipoDeActividad;
    }

    public int getCantidadCheckIns() {
        return cantidadCheckIns;
    }

    public void setCantidadCheckIns(int cantidadCheckIns) {
        this.cantidadCheckIns = cantidadCheckIns;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }
}
