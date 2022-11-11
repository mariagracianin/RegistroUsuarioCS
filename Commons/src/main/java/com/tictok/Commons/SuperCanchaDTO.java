package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuperCanchaDTO {
    private String nombreServicio;
    private String nombreCentro;
    private Double precio;
    private String address;
    private String barrio;
    private String telefono;
    private String imageString;
    private List<HorarioDTO> horarios;

    public SuperCanchaDTO(){
    }

    public SuperCanchaDTO(String nombreServicio, String nombreCentro, Double precio, String address, String barrio, String telefono, List<HorarioDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.horarios = horarios;
    }

    public SuperCanchaDTO(String nombreServicio, String nombreCentro, Double precio, String address, String barrio, String telefono, String imageString, List<HorarioDTO> horarios) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageString = imageString;
        this.horarios = horarios;
    }

    public void addHorario(HorarioDTO horarioDTO){
        horarios.add(horarioDTO);
    }
    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }


}
