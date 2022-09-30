package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuentaDTO {

    private String mail;
    private String password;
    private String entidadPadre;
    private String tipo;

    public CuentaDTO(String mail, String password, String entidadPadre, String tipo) {
        this.mail = mail;
        this.password = password;
        this.entidadPadre = entidadPadre;
        this.tipo = tipo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
