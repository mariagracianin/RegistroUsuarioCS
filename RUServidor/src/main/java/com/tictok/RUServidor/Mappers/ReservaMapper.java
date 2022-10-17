package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUServidor.Entities.ReservaActividad;
import com.tictok.RUServidor.Entities.ReservaCancha;

public class ReservaMapper {

    public static ReservaDTO fromReservaCanchaToReservaDTO(ReservaCancha reservaCancha){
        int cedula = reservaCancha.getUsuario().getCedula();
        String nombreCentro = reservaCancha.getCancha().getCanchaId().getCentroDeportivo();
        String nombreActividad = reservaCancha.getCancha().getCanchaId().getNombreServicio();
        String tipo = "Cancha";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorario(reservaCancha.getCancha().getCanchaId());
        Long codigoReserva = reservaCancha.getId();
        return new ReservaDTO(cedula, nombreCentro, nombreActividad, tipo, horario, codigoReserva);
    }

    public static ReservaDTO fromReservaActividadToReservaDTO(ReservaActividad reservaActividad) {
        int cedula = reservaActividad.getUsuario().getCedula();
        String nombreCentro = reservaActividad.getActividad().getActividadId().getCentroDeportivo();
        String nombreActividad = reservaActividad.getActividad().getActividadId().getNombreServicio();
        String tipo = "Actividad";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorario(reservaActividad.getActividad().getActividadId());
        Long codigoReserva = reservaActividad.getId();
        return new ReservaDTO(cedula, nombreCentro, nombreActividad, tipo, horario, codigoReserva);
    }
}
