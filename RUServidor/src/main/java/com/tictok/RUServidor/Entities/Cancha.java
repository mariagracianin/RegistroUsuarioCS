package com.tictok.RUServidor.Entities;

import javax.persistence.*;

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
