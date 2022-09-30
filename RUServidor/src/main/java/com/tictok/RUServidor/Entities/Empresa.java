package com.tictok.RUServidor.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Empresa {

    @Id
    private String nombreEmpresa;

    @Column
    private String password;

    @Column
    private String newCoso;

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

//    @OneToMany(mappedBy = "cuenta")
//    private List<Cuenta>

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
