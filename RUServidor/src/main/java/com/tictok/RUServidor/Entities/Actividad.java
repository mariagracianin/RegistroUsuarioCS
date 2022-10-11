package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Actividad {
    @Id
    @Column(name = "nombre_actividad", nullable = false)
    private String nombreActividad;


    @ManyToOne
    @PrimaryKeyJoinColumn (name = "centro_deportivo_nombre_centro")
    private CentroDeportivo centroDeportivo;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @ElementCollection
    @CollectionTable(name = "actividad_horario", joinColumns = @JoinColumn(name = "id_actividad"))
    private List<Horario> horario = new ArrayList<>();

    @Column(name = "cupos", nullable = false)
    private Integer cupos;


    public Actividad() {
    }

    public Actividad(String nombreActividad, Integer precio, List<Horario> horario, Integer cupos) {
        this.nombreActividad = nombreActividad;
        this.precio = precio;
        this.horario = horario;
        this.cupos = cupos;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
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

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombre) {
        this.nombreActividad = nombre;
    }

    public CentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(CentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }
}
