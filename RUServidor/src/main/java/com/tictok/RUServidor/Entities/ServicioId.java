package com.tictok.RUServidor.Entities;

import java.io.Serializable;
import java.util.Objects;


public class ServicioId implements Serializable {
    private String nombreServicio;
    private CentroDeportivo centroDeportivo;

    public ServicioId() {
    }

    public ServicioId(String nombreActividad, CentroDeportivo centroDeportivo) {
        this.nombreServicio = nombreActividad;
        this.centroDeportivo = centroDeportivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioId that = (ServicioId) o;
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

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
