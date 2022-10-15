package com.tictok.RUServidor.Entities;

import  javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Empresa {

    @Id
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "telefono", nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(name = "encargado", nullable = false, length = 50)
    private String encargado;

    @Column(name = "rut", nullable = false, length = 50)
    private String rut;

    @Column(name = "razon_social", nullable = false, length = 50)
    private String razonSocial;

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Empresa() {
    }

    public Empresa(String nombreEmpresa, String adress, String telefono, String encargado, String rut, String razonSocial) {
        this.nombreEmpresa = nombreEmpresa;
        this.adress = adress;
        this.telefono = telefono;
        this.encargado = encargado;
        this.rut = rut;
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setCuenta(Cuenta cuenta){this.cuentas.add(cuenta);}

//    @OneToMany(mappedBy = "cuenta")
//    private List<Cuenta>

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
