package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Usuario {
    @Id
    private String mail;
    @Column (name = "password", nullable = false)
    private String password;

    @Column (name = "cedula", nullable = false)
    private int cedula;

    @Column (name = "vencimiento_carne", nullable = true)
    private String vencimientoCarne;

    @Column (name= "nombre", nullable = false)
    private String nombre;
    @Column (name = "apellido", nullable = false)
    private String apellido;

    @Column (name = "telefono", nullable = true)
    private String telefono;
    @Column (name = "saldoBase", nullable = false)
    private double saldoBase;
    @Column(name = "sobregiro", nullable = false)
    private double sobregiro;
    @Column
    private double saldo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_nombre_empresa", nullable = false)
    private Empresa empresa;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "cuenta_mail", nullable = false)
    private Cuenta cuenta;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario(String mail, String password, int cedula, String vencimientoCarne,
                   String nombre, String apellido, String telefono, double saldoBase,
                   double sobregiro, double saldo) {
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

    public boolean telefonoCorrecto(){
        return true;
    }

    public String getVencimientoCarne() {
        return vencimientoCarne;
    }

    public void setVencimientoCarne(String vencimientoCarne) {
        this.vencimientoCarne = vencimientoCarne;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getSaldoBase() {
        return saldoBase;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String direc) {
        this.apellido = direc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
