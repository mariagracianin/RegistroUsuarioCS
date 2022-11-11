package com.tictok.Commons;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioDTO {
    private int dia;
    private int horaInicio;
    private int horaFin;

    public HorarioDTO(int dia, int horaInicio, int horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public HorarioDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioDTO that = (HorarioDTO) o;
        return dia == that.dia && horaInicio == that.horaInicio && horaFin == that.horaFin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, horaInicio, horaFin);
    }


}
