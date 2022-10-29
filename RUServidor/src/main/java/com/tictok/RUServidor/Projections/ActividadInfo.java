package com.tictok.RUServidor.Projections;

/**
 * A Projection for the {@link com.tictok.RUServidor.Entities.Actividad} entity
 */
public class ActividadInfo {
    private String nombreCentro;
    private String nombreActividad;
    private Integer cupos;
    private Integer precio;
    private Boolean paseLibre;
    private String address;
    private String barrio;
    private String telefono;
    private String imageString;

    public ActividadInfo(String centroDeportivo, String nombreActividad, Integer precio, Boolean paseLibre, String address, String barrio, String telefono, String imageString) {
        this.nombreCentro = centroDeportivo;
        this.nombreActividad = nombreActividad;
        this.precio = precio;
        this.paseLibre = paseLibre;
        this.address = address;
        this.barrio = barrio;
        this.telefono = telefono;
        this.imageString = imageString;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
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

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}