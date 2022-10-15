package com.tictok.RUServidor.Entities.NotTables;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
public class Horario implements Serializable {

    private DayOfWeek dia;
    private LocalTime horaInicio;

    private LocalTime horaFin;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Horario() {

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

    public void setHoraInicio(LocalTime hora) {
        this.horaInicio = hora;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
