package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "check_in_cancha")
public class CheckInCancha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reserva_cancha_padre_id", nullable = false)
    private ReservaCancha reservaCanchaPadre;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_cedula", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "CANCHA_NOMBRESERVICIO", referencedColumnName = "NOMBRESERVICIO", nullable = false),
            @JoinColumn(name = "CANCHA_CENTRODEPORTIVO", referencedColumnName = "CENTRO_DEPORTIVO_NOMBRE_CENTRO", nullable = false),
            @JoinColumn(name = "CANCHA_DIA", referencedColumnName = "DIA", nullable = false),
            @JoinColumn(name = "CANCHA_HORAINICIO", referencedColumnName = "HORAINICIO", nullable = false),
            @JoinColumn(name = "CANCHA_HORAFIN", referencedColumnName = "HORAFIN", nullable = false)
    })
    private Cancha cancha;

    @Column(name = "precio", nullable = false)
    private Double precio;

    public CheckInCancha() {
    }

    public CheckInCancha(ReservaCancha reservaCanchaPadre, Date fecha, Usuario usuario, Cancha cancha, Double precio) {
        this.reservaCanchaPadre = reservaCanchaPadre;
        this.fecha = fecha;
        this.usuario = usuario;
        this.cancha = cancha;
        this.precio = precio;
    }

    public CheckInCancha(Date fecha, Usuario usuario, Cancha cancha, Double precio) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.cancha = cancha;
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
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

    public ReservaCancha getReservaCanchaPadre() {
        return reservaCanchaPadre;
    }
    public void setReservaCanchaPadre(ReservaCancha reservaCanchaPadre) {
        this.reservaCanchaPadre = reservaCanchaPadre;
    }

}