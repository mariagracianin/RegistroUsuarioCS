package com.tictok.RUServidor.Entities;

import javax.persistence.*;

@Entity
@Table
public class Cancha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nombre_cancha", nullable = false, length = 100)
    private String nombreCancha;

    public String getNombreCancha() {
        return nombreCancha;
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }
}
