package com.tictok.RUServidor.Entities.NotTables;

import java.util.Objects;

public class ServicioIdSinHorario {
    private String nombreServicio;
    private String centroDeportivo;

    public ServicioIdSinHorario(String nombreServicio, String centroDeportivo) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioIdSinHorario that = (ServicioIdSinHorario) o;
        return Objects.equals(nombreServicio, that.nombreServicio) && Objects.equals(centroDeportivo, that.centroDeportivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreServicio, centroDeportivo);
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(String centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
