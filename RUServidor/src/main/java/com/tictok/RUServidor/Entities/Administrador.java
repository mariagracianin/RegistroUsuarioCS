package com.tictok.RUServidor.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @Column(name = "mail", nullable = false)
    private String mail;

    public Administrador(String mail, String password, String entidadPadre, String tipo) {
        this.mail = mail;
        this.password = password;
        this.entidadPadre = entidadPadre;
        this.tipo = tipo;
    }

    public Administrador() {
    }

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "entidad_padre", length = 150)
    private String entidadPadre;

    @Column(name = "tipo", length = 10)
    private String tipo;

    public String getEntidadPadre() {
        return entidadPadre;
    }

    public void setEntidadPadre(String entidadPadre) {
        this.entidadPadre = entidadPadre;
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

}