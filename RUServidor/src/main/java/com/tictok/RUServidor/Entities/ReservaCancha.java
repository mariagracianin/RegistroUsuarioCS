package com.tictok.RUServidor.Entities;

import javax.persistence.*;

@Entity
@Table
public class ReservaCancha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_principal_cedula", nullable = false)
    private Usuario usuarioPrincipal;

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

    public Usuario getUsuarioPrincipal() {
        return usuarioPrincipal;
    }

    public void setUsuarioPrincipal(Usuario usuarioPrincipal) {
        this.usuarioPrincipal = usuarioPrincipal;
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
