package com.tictok.Commons;

public class CentroDeportivoDTO {
    private String nombreCentro;
    private String address;
    private String barrio;
    private String telefono;
    private String encargado;
    private String rut;
    private String razonSocial;

    public CentroDeportivoDTO() {
    }

    public CentroDeportivoDTO(String nombreCentro, String address, String barrio, String telefono, String encargado, String rut, String razonSocial) {
        this.nombreCentro = nombreCentro;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.encargado = encargado;
        this.rut = rut;
        this.razonSocial = razonSocial;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
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
