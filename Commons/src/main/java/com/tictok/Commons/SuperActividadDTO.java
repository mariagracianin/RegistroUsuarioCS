package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperActividadDTO {
    private String nombreServicio;
    private String nombreCentro;
    private Integer precio;
    private Boolean paseLibre;
    private String address;
    private String barrio;
    private String telefono;
    private String imageSrc;
    private List<HorarioDTO> horarios;

    public SuperActividadDTO() {
    }

    public SuperActividadDTO(String nombreServicio, String nombreCentro, Integer precio, Boolean paseLibre, String address, String barrio, String telefono, String imageSrc, List<HorarioDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.paseLibre = paseLibre;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageSrc = imageSrc;
        this.horarios = horarios;
    }

    public void addHorario(HorarioDTO horarioDTO){
        horarios.add(horarioDTO);
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

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }
}
