package com.tictok.Commons.Resumenes;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResumenDTO {
    private int cedula;
    private String nombre;
    private String apellido;
    private int cantidadCheckIns;
    private double importe;
    private double saldoBase;
    private double saldo;
    private double sobregiro;

    public UsuarioResumenDTO() {
    }

    public UsuarioResumenDTO(int cedula, String nombre, String apellido, int cantidadCheckIns, double importe, double saldoBase, double saldo, double sobregiro) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadCheckIns = cantidadCheckIns;
        this.importe = importe;
        this.saldoBase = saldoBase;
        this.saldo = saldo;
        this.sobregiro = sobregiro;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantidadCheckIns() {
        return cantidadCheckIns;
    }

    public void setCantidadCheckIns(int cantidadCheckIns) {
        this.cantidadCheckIns = cantidadCheckIns;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getSaldoBase() {
        return saldoBase;
    }

    public void setSaldoBase(double saldoBase) {
        this.saldoBase = saldoBase;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        this.sobregiro = sobregiro;
    }
}
