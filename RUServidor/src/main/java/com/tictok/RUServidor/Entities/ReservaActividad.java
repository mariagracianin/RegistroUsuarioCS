package com.tictok.RUServidor.Entities;

import javax.persistence.*;

@Entity
@Table
public class ReservaActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
