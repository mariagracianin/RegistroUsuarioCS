package com.tictok.RUServidor.Entities;

import  javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Empresa {

    @Id
    private String nombreEmpresa;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "telefono", nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(name = "encargado", nullable = false, length = 50)
    private String encargado;

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Empresa(String nombreEmpresa, String adress, String telefono, String encargado) {
        this.nombreEmpresa = nombreEmpresa;
        this.adress = adress;
        this.telefono = telefono;
        this.encargado = encargado;
    }

    public Empresa() {
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
}
