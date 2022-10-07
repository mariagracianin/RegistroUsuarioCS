package com.tictok.RUServidor.Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
public class Horario implements Serializable {

    private DayOfWeek dia;
    private LocalTime hora;

    public Horario(DayOfWeek dia, LocalTime hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public Horario() {

    }

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
