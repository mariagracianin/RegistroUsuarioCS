package com.tictok.RUServidor.Projections;

/**
 * A Projection for the {@link com.tictok.RUServidor.Entities.CentroDeportivo} entity
 */
public interface CentroDeportivoInfo {
    String getNombreCentro();

    String getAddress();

    String getBarrio();

    String getTelefono();

    String getEncargado();

    String getRut();

    String getRazonSocial();
}