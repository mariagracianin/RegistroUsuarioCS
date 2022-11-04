package com.tictok.RUServidor.Projections;

import com.tictok.RUServidor.Entities.NotTables.ServicioId;

import java.util.Objects;

public class CuentaCheckIns {

    private final ServicioId servicioId;
    private final long cupos;

    public CuentaCheckIns(ServicioId servicioId, long cupos) {
        this.servicioId = servicioId;
        this.cupos = cupos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicioId);
    }

    public ServicioId getServicioId() {
        return servicioId;
    }

    public long getCupos() {
        return cupos;
    }
}
