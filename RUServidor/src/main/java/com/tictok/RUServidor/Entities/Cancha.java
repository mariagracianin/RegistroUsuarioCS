package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@IdClass(ServicioId.class)
public class Cancha {
    @Id
    @Column(name = "nombre_cancha", nullable = false, length = 100)
    private String nombreServicio;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_nombre_centro", nullable = false)
    private CentroDeportivo centroDeportivo;

    @Id
    @Column(name = "dia")
    private DayOfWeek dia;

    @Id
    private LocalTime horaInicio;

    @Id
    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;


    public Cancha() {
    }

    public Cancha(String nombreServicio, CentroDeportivo centroDeportivo, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer precio, Integer cupos) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.cupos = cupos;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
