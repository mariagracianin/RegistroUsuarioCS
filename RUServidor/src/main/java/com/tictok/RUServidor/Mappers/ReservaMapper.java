package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
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

}
