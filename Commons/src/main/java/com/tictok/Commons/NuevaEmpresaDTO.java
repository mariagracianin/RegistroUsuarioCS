package com.tictok.Commons;

public class NuevaEmpresaDTO {

    private String mail;
    private String password;
    private String nombreEmpresa;
    private String adress;
    private String telefono;
    private String encargado;

    public NuevaEmpresaDTO(String mail, String password, String nombreEmpresa, String adress, String telefono, String encargado) {
        this.mail = mail;
        this.password = password;
        this.nombreEmpresa = nombreEmpresa;
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
}
