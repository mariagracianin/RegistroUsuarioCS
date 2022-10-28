package com.tictok.RUServidor.Projections;

/**
 * A Projection for the {@link com.tictok.RUServidor.Entities.Actividad} entity
 */
public interface ActividadInfo {
    Integer getPrecio();

    Integer getCupos();

    Boolean isPaseLibre();

    ServicioIdInfo getActividadId();

    CentroDeportivoInfo getCentroDeportivo();

    ImagenInfo getImagen();
}