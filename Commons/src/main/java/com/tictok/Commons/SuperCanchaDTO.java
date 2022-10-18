package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperCanchaDTO {
    private String nombreServicio;
    private Integer precio;
    private String nombreCentro;
    private String address;
    private String barrio;
    private String telefono;
    private List<HorarioDTO> horarios;
    private String imageSrc;

    public SuperCanchaDTO(){
    }

    public SuperCanchaDTO(String nombreServicio, Integer precio, String nombreCeentro, String address, String barrio, String telefono, List<HorarioDTO> horarios, String imageSrc) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.nombreCentro = nombreCeentro;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.horarios = horarios;
        this.imageSrc = imageSrc;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCeentro) {
        this.nombreCentro = nombreCeentro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarrio() {
        return barrio;
    }
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
