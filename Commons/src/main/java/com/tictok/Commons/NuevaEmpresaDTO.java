package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class NuevaEmpresaDTO {

    private String mail;
    private String password;
    private String nombreEmpresa;
    private String adress;
    private String telefono;
    private String encargado;
    private String rut;
    private String razonSocial;

    public NuevaEmpresaDTO() {
    }

    public NuevaEmpresaDTO(String mail, String password, String nombreEmpresa, String adress, String telefono, String encargado, String rut, String razonSocial) {
        this.mail = mail;
        this.password = password;
        this.nombreEmpresa = nombreEmpresa;
        this.adress = adress;
        this.telefono = telefono;
        this.encargado = encargado;
        this.rut = rut;
        this.razonSocial = razonSocial;
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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
