package com.tictok.Commons;

public class BalanceDTO {
    String nombre;
    String encargado;
    String address;
    String telefono;
    String tipo;
    String rut;
    int cantidadDeCheckIns;
    int cantidadUsuarios;

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(int cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }

    Double importe;

    public BalanceDTO() {
    }

    public BalanceDTO(String nombre, String encargado, String address, String telefono, String tipo, String rut, int cantidadDeCheckIns, Double importe) {
        this.nombre = nombre;
        this.encargado = encargado;
        this.address = address;
        this.telefono = telefono;
        this.tipo = tipo;
        this.rut = rut;
        this.cantidadDeCheckIns = cantidadDeCheckIns;
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getCantidadDeCheckIns() {
        return cantidadDeCheckIns;
    }

    public void setCantidadDeCheckIns(int cantidadDeCheckIns) {
        this.cantidadDeCheckIns = cantidadDeCheckIns;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
