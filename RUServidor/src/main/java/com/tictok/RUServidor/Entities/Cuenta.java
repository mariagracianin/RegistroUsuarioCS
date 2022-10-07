package com.tictok.RUServidor.Entities;

import javax.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @Column(name = "mail", nullable = false)
    private String mail;

    @ManyToOne
    @JoinColumn(name = "empresa_nombre_empresa")
    private Empresa empresa;

    @OneToOne(mappedBy = "cuenta", orphanRemoval = true)
    private Usuario usuario;

    public Cuenta(String mail, String password, String tipo) {
        this.mail = mail;
        this.password = password;
        this.tipo = tipo;
    }

    public Cuenta() {
    }

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "tipo", length = 10)
    private String tipo;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return mail;
    }

    public void setId(String mail ) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}