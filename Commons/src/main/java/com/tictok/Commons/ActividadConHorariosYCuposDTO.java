package com.tictok.Commons;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActividadConHorariosYCuposDTO {
    private String nombreServicio;
    private String nombreCentro;
    private Double precio;
    private String address;
    private String barrio;
    private String telefono;
    private List<HorarioConCuposDTO> horariosConCupos;

    public ActividadConHorariosYCuposDTO() {
    }

    public ActividadConHorariosYCuposDTO(String nombreServicio, String nombreCentro, Double precio, String address, String barrio, String telefono, List<HorarioConCuposDTO> horariosConCupos) {
        this.nombreServicio = nombreServicio;
        this.nombreCentro = nombreCentro;
        this.precio = precio;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.horariosConCupos = horariosConCupos;
    }

    public void addHorarioConCupos(HorarioConCuposDTO horarioConCuposDTO) {
        horariosConCupos.add(horarioConCuposDTO);
    }
    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public List<HorarioConCuposDTO> getHorariosConCupos() {
        return horariosConCupos;
    }

    public void setHorariosConCupos(List<HorarioConCuposDTO> horariosConCupos) {
        this.horariosConCupos = horariosConCupos;
    }


}
