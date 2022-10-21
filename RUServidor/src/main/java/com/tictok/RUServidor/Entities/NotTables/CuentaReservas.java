package com.tictok.RUServidor.Entities.NotTables;


import java.util.Objects;

public class CuentaReservas {
    private final ServicioId servicioId;
    private final long cupos;

    public CuentaReservas(ServicioId servicioId, long cupos) {
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
