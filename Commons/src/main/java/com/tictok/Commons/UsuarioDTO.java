package com.tictok.Commons;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private String mail;
    private String password;
    private int cedula;
    private LocalDate vencimientoCarne;
    private String nombre;
    private String apellido;
    private String telefono;
    private double saldoBase;
    private double sobregiro;
    private double saldo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String mail, String password, int cedula, LocalDate vencimientoCarne, String nombre, String apellido, String telefono, double saldoBase, double sobregiro, double saldo) {
        this.mail = mail;
        this.password = password;
        this.cedula = cedula;
        this.vencimientoCarne = vencimientoCarne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.saldoBase = saldoBase;
        this.sobregiro = sobregiro;
        this.saldo = saldo;
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

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public LocalDate getVencimientoCarne() {
        return vencimientoCarne;
    }

    public void setVencimientoCarne(LocalDate vencimientoCarne) {
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
}
