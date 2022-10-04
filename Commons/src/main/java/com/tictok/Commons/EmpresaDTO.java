package com.tictok.Commons;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO {

    private String nombreEmpresa;
    private String adress;
    private String telefono;
    private String encargado;
    private List<CuentaDTO> cuentas = new ArrayList<CuentaDTO>();

    public EmpresaDTO(String nombreEmpresa, String adress, String telefono, String encargado, List<CuentaDTO> cuentas) {
        this.nombreEmpresa = nombreEmpresa;
        this.adress = adress;
        this.telefono = telefono;
        this.encargado = encargado;
        this.cuentas = cuentas;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }
}
