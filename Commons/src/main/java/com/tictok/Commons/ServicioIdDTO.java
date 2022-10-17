package com.tictok.Commons;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ServicioIdDTO {
    private String nombreServicio;
    private String centroDeportivo;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public ServicioIdDTO() {
    }

    public ServicioIdDTO(String nombreServicio, String centroDeportivo, String dia, String horaInicio, String horaFin) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
