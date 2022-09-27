package com.tictok.RUServidor.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_empresa")
public class UsuarioEmpresa {
    @Id
    @Column(nullable = false, length = 100)
    private String mail;



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}