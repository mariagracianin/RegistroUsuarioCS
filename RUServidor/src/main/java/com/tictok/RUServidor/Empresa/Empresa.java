package com.tictok.RUServidor.Empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Empresa {

    @Id
    private String nombreEmpresa;

    @Column
    private String password;

    @Column
    private String newCoso;
}
