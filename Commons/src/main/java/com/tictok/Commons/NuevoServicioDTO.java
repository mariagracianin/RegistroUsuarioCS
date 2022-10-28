package com.tictok.Commons;

import java.util.List;

public class NuevoServicioDTO {
    private String nombreServicio;
    private Integer precio;
    private Integer cupos;
    private Boolean paseLibre;
    private String imageString;
    private List<HorarioDTO> horarios;

    public NuevoServicioDTO() {
    }

    public NuevoServicioDTO(String nombreServicio, Integer precio, Integer cupos, Boolean paseLibre, String imageString, List<HorarioDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.cupos = cupos;
        this.paseLibre = paseLibre;
        this.imageString = imageString;
        this.horarios = horarios;
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

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public List<HorarioDTO> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioDTO> horarios) {
        this.horarios = horarios;
    }
}