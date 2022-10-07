package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class NuevoCentroDTO {

    private String mail;
    private String password;
    private String nombreCentro;
    private String adress;
    private String telefono;
    private String encargado;

    public NuevoCentroDTO() {
    }

    public NuevoCentroDTO(String mail, String password, String nombreCentro, String adress, String telefono, String encargado) {
        this.mail = mail;
        this.password = password;
        this.nombreCentro = nombreCentro;
        this.adress = adress;
        this.telefono = telefono;
        this.encargado = encargado;
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

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
}
