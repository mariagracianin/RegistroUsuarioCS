package com.tictok.RUServidor.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "centro_deportivo")
public class CentroDeportivo {
    @Id
    @Column(name = "nombre_centro")
    private String nombreCentro;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telefono", nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(name = "encargado", nullable = false, length = 50)
    private String encargado;

    @OneToMany(mappedBy = "centroDeportivo", orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    @OneToMany(mappedBy = "centroDeportivo", orphanRemoval = true)
    private List<Actividad> actividades = new ArrayList<>();

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public CentroDeportivo() {
    }

    public CentroDeportivo(String nombreCentro, String address, String telefono, String encargado) {
        this.nombreCentro = nombreCentro;
        this.address = address;
        this.telefono = telefono;
        this.encargado = encargado;
    }



}
