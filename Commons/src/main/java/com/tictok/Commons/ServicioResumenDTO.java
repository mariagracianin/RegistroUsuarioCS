package com.tictok.Commons;

public class ServicioResumenDTO {
    private String nombreActividad;
    private String tipoDeActividad;
    private int cantidadCheckIns;
    private Double importeTotal;


    public ServicioResumenDTO() {
    }

    public ServicioResumenDTO(String nombreActividad, String tipoDeActividad, int cantidadCheckIns, Double importeTotal) {
        this.nombreActividad = nombreActividad;
        this.tipoDeActividad = tipoDeActividad;
        this.cantidadCheckIns = cantidadCheckIns;
        this.importeTotal = importeTotal;
    }
}
