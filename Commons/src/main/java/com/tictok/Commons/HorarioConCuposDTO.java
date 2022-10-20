package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioConCuposDTO {
    private int dia;
    private int horaInicio;
    private int horaFin;

    private int cuposLibres;

    public HorarioConCuposDTO(int dia, int horaInicio, int horaFin, int cuposLibres) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cuposLibres = cuposLibres;
    }

    public HorarioConCuposDTO() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getCuposLibres() {
        return cuposLibres;
    }

    public void setCuposLibres(int cuposLibres) {
        this.cuposLibres = cuposLibres;
    }
}
