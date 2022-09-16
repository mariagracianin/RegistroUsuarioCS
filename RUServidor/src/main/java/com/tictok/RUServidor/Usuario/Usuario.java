package com.tictok.RUServidor.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario {


    @Column (name= "nombre")
    private String nombre;
    @Column
    private String apellido;
    @Id
    private String telefono;

    public Usuario(String nombre, String direc, String telefono) {
        this.nombre = nombre;
        this.apellido = direc;
        this.telefono = telefono;
    }

    public Usuario() {

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

    public void setApellido(String direc) {
        this.apellido = direc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
