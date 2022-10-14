package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HorarioDTO {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public HorarioDTO() {
    }

    public HorarioDTO(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioDTO entity = (HorarioDTO) o;
        return Objects.equals(this.dia, entity.dia) &&
                Objects.equals(this.horaInicio, entity.horaInicio) &&
                Objects.equals(this.horaFin, entity.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, horaInicio, horaFin);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "dia = " + dia + ", " +
                "horaInicio = " + horaInicio + ", " +
                "horaFin = " + horaFin + ")";
    }
}