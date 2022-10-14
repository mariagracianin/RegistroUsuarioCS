package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@IdClass(ServicioId.class)
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "actividad_nombre", nullable = false)
    private String nombreServicio;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_nombre_centro", nullable = false)
    private CentroDeportivo centroDeportivo;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;

    @ElementCollection
    @CollectionTable(name = "actividad_horario", joinColumns = {
            @JoinColumn(name = "actividad_id"),
            @JoinColumn(name = "centro_deportivo_id")
        }
    )
    private List<Horario> horario = new ArrayList<>();

    @Column(name = "pase_libre", nullable = false)
    private Boolean paseLibre = false;

    public Actividad() {
    }

    public Actividad(String nombreServicio, CentroDeportivo centroDeportivo, Integer precio, Integer cupos, List<Horario> horario, Boolean paseLibre) {
        this.nombreServicio = nombreServicio;
        this.centroDeportivo = centroDeportivo;
        this.precio = precio;
        this.cupos = cupos;
        this.horario = horario;
        this.paseLibre = paseLibre;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
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
