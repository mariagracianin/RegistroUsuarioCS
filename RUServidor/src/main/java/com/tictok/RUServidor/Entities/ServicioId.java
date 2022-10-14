package com.tictok.RUServidor.Entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;


public class ServicioId implements Serializable {
    private String nombreServicio;
    private CentroDeportivo centroDeportivo;
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public ServicioId() {
    }

    public ServicioId(String nombreServicio, CentroDeportivo centroDeportivo, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioId that = (ServicioId) o;
        return Objects.equals(nombreServicio, that.nombreServicio) && Objects.equals(centroDeportivo, that.centroDeportivo) && dia == that.dia && Objects.equals(horaInicio, that.horaInicio) && Objects.equals(horaFin, that.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreServicio, centroDeportivo, dia, horaInicio, horaFin);
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

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
