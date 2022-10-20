package com.tictok.RUServidor.Entities;

import com.tictok.RUServidor.Entities.NotTables.ServicioId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table
public class Cancha {

    @EmbeddedId
    private ServicioId canchaId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "centro_deportivo_nombre_centro", nullable = false)
    @MapsId("centroDeportivo")
    private CentroDeportivo centroDeportivo;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "cupos")
    private Integer cupos;

    @ManyToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }


    public Cancha() {
    }

    public Cancha(CentroDeportivo centroDeportivo, String nombreActividad,
                     DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Integer precio, Integer cupos) {
        this.canchaId = new ServicioId(dia, horaInicio, horaFin);
        this.canchaId.setNombreServicio(nombreActividad);
        this.canchaId.setCentroDeportivo(centroDeportivo.getNombreCentro());
        this.centroDeportivo = centroDeportivo;
        this.precio = precio;
        this.cupos = cupos;
    }

    public ServicioId getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(ServicioId canchaId) {
        this.canchaId = canchaId;
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
