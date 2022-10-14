package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperActividadDTO {
    private String nombreServicio;
    private Integer precio;
    private Integer cupos; //ver
    private Boolean paseLibre; //ver
    private String nombreCentro;
    private String address;
    private String barrio;
    private String telefono;
    private List<String> horarios;
    private String imageSrc;

    public SuperActividadDTO() {
    }

    public SuperActividadDTO(String nombreServicio, Integer precio, Integer cupos, Boolean paseLibre, String nombreCentro, String address, String barrio, String telefono, List<String> horarios, String imageSrc) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.cupos = cupos;
        this.paseLibre = paseLibre;
        this.nombreCentro = nombreCentro;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.horarios = horarios;
        this.imageSrc = imageSrc;
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

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
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

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }
}
