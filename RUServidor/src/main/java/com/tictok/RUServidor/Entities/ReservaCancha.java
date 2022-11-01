package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class ReservaCancha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_cancha_padre_id")
    private ReservaCancha reservaCanchaPadre;

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

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    public ReservaCancha() {
    }

    public ReservaCancha(Usuario usuario, Cancha cancha, Date fecha) {
        this.usuario = usuario;
        this.cancha = cancha;
        this.fecha = fecha;

    }

    public ReservaCancha getReservaCanchaPadre() {
        return reservaCanchaPadre;
    }

    public void setReservaCanchaPadre(ReservaCancha reservaCanchaPadre) {
        this.reservaCanchaPadre = reservaCanchaPadre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
