package com.tictok.RUServidor.Entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "centro_deportivo")
public class CentroDeportivo {
    @Id
    @Column(name = "nombre_centro")
    private String nombreCentro;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "barrio", length = 50)
    private String barrio;

    @Column(name = "telefono", nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(name = "encargado", nullable = false, length = 50)
    private String encargado;

    @Column(name = "rut", nullable = false, length = 50)
    private String rut;

    @Column(name = "razon_social", nullable = false, length = 50)
    private String razonSocial;

    @OneToMany(mappedBy = "centroDeportivo", orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    @OneToMany(mappedBy = "centroDeportivo", orphanRemoval = true)
    private List<Cancha> canchas = new ArrayList<>();

    @OneToMany(mappedBy = "centroDeportivo", orphanRemoval = true)
    private List<Actividad> actividades = new ArrayList<>();

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Cancha> getCanchas() {
        return canchas;
    }

    public void setCanchas(List<Cancha> canchas) {
        this.canchas = canchas;
    }

    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombreCentro, String address, String barrio, String telefono, String encargado, String rut, String razonSocial) {
        this.nombreCentro = nombreCentro;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.encargado = encargado;
        this.rut = rut;
        this.razonSocial = razonSocial;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }


    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setCuenta(Cuenta cuenta){this.cuentas.add(cuenta);}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroDeportivo that = (CentroDeportivo) o;
        return nombreCentro.equals(that.nombreCentro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCentro);
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

    public void setActividad(Actividad actividad){
        this.getActividades().add(actividad);
    }
}
