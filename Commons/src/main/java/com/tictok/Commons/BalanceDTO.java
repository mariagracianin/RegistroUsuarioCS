package com.tictok.Commons;

public class BalanceDTO {
    String nombre;
    String tipo;
    String rut;
    String importe;

    public BalanceDTO() {
    }

    public BalanceDTO(String nombre, String tipo, String rut, String importe) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.rut = rut;
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
}
