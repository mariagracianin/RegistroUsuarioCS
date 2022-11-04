package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "check_in_actividad")
public class CheckInActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

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

    @Column(name = "precio", nullable = false)
    private Double precio;

    public CheckInActividad() {
    }

    public CheckInActividad(Usuario usuario, Date fecha, Actividad actividad) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.actividad = actividad;
        this.precio = actividad.getPrecio();
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}