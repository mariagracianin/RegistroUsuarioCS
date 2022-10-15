package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperActividadDTO {
    private String nombreServicio;
    private Integer precio;
    private Boolean paseLibre;
    private String nombreCentro;
    private String address;
    private String barrio;
    private String telefono;
    private String imageSrc;
    private List<HorarioConCuposDTO> horarios;

    public SuperActividadDTO() {
    }

    public SuperActividadDTO(String nombreServicio, Integer precio, Boolean paseLibre, String nombreCentro, String address, String barrio, String telefono, String imageSrc, List<HorarioConCuposDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.paseLibre = paseLibre;
        this.nombreCentro = nombreCentro;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageSrc = imageSrc;
        this.horarios = horarios;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
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

    public Boolean getPaseLibre() {
        return paseLibre;
    }

    public void setPaseLibre(Boolean paseLibre) {
        this.paseLibre = paseLibre;
    }

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

    public List<HorarioConCuposDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioConCuposDTO> horarios) {
        this.horarios = horarios;
    }
}
