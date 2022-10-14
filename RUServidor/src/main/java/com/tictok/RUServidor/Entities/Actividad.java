package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@IdClass(ServicioId.class)
public class Actividad {
    @Id
    @Column(name = "actividad_nombre", nullable = false)
    private String nombreServicio;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_nombre_centro", nullable = false)
    private CentroDeportivo centroDeportivo;

    @Id
    private DayOfWeek dia;

    @Id
    private LocalTime horaInicio;

    @Id
    private LocalTime horaFin;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;

    @Column(name = "pase_libre", nullable = false)
    private Boolean paseLibre = false;

    public Actividad() {
    }

    public Actividad(String nombreServicio, CentroDeportivo centroDeportivo, DayOfWeek dia, LocalTime horaInicio,
                     LocalTime horaFin, Integer precio, Integer cupos, Boolean paseLibre) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.cupos = cupos;
        this.paseLibre = paseLibre;
    }

    public Boolean getPaseLibre() {
        return paseLibre;
    }

    public void setPaseLibre(Boolean paseLibre) {
        this.paseLibre = paseLibre;
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
