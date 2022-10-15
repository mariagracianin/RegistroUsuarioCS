package com.tictok.RUServidor.Entities;

import com.tictok.RUServidor.Entities.NotTables.ServicioId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table
public class Actividad {
    @EmbeddedId
    private ServicioId actividadId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_nombre_centro", nullable = false)
    @MapsId("centroDeportivo")
    private CentroDeportivo centroDeportivo;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;

    @Column(name = "pase_libre", nullable = false)
    private Boolean paseLibre = false;

    public Actividad() {
    }

    public Actividad(CentroDeportivo centroDeportivo, String nombreActividad,
                     DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer precio, Integer cupos, Boolean paseLibre) {
        this.actividadId = new ServicioId(dia, horaInicio, horaFin);
        this.actividadId.setNombreServicio(nombreActividad);
        this.actividadId.setCentroDeportivo(centroDeportivo.getNombreCentro());
        this.centroDeportivo = centroDeportivo;
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


}
