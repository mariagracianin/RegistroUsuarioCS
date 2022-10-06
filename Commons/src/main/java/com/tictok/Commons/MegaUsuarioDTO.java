package com.tictok.Commons;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MegaUsuarioDTO {

    private String cuentaMail;
    private String password;
    private int cedula;
    private String vencimientoCarne;
    private String nombre;
    private String apellido;
    private String telefono;
    private double saldoBase;
    private double sobregiro;
    private double saldo;
    private String address;

    public MegaUsuarioDTO() {
    }

    public MegaUsuarioDTO(String cuentaMail,String password, int cedula, String vencimientoCarne, String nombre, String apellido, String telefono,
                          double saldoBase, double sobregiro, double saldo, String address) {
        this.cuentaMail = cuentaMail;
        this.password = password;
        this.cedula = cedula;
        this.vencimientoCarne = vencimientoCarne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.saldoBase = saldoBase;
        this.sobregiro = sobregiro;
        this.saldo = saldo;
        this.address = address;
    }


    public String getCuentaMail() {
        return cuentaMail;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCuentaMail(String cuentaMail) {
        this.cuentaMail = cuentaMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getVencimientoCarne() {
        return vencimientoCarne;
    }

    public void setVencimientoCarne(String vencimientoCarne) {
        this.vencimientoCarne = vencimientoCarne;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldoBase() {
        return saldoBase;
    }

    public void setSaldoBase(double saldoBase) {
        this.saldoBase = saldoBase;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        this.sobregiro = sobregiro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}