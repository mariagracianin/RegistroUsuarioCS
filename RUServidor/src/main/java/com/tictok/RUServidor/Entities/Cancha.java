package com.tictok.RUServidor.Entities;

import javax.persistence.*;
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

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;

    @ElementCollection
    @CollectionTable(name = "cancha_horario", joinColumns = {
            @JoinColumn(name = "cancha_id"),
            @JoinColumn(name = "centro_deportivo_id")
    }
    )
    private List<Horario> horarios = new ArrayList<>();
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
