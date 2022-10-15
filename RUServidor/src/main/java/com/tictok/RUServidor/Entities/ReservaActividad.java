package com.tictok.RUServidor.Entities;

import javax.persistence.*;

@Entity
@Table
public class ReservaActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_cedula", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "ACTIVIDAD_NOMBRESERVICIO", referencedColumnName = "NOMBRESERVICIO", nullable = false),
            @JoinColumn(name = "ACTIVIDAD_CENTRODEPORTIVO", referencedColumnName = "CENTRO_DEPORTIVO_nombre_centro", nullable = false),
            @JoinColumn(name = "ACTIVIDAD_DIA", referencedColumnName = "DIA", nullable = false),
            @JoinColumn(name = "ACTIVIDAD_HORAINICIO", referencedColumnName = "HORAINICIO", nullable = false),
            @JoinColumn(name = "ACTIVIDAD_HORAFIN", referencedColumnName = "HORAFIN", nullable = false)
    })
    private Actividad actividad;

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
