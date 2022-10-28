package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.Reserva2DTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUServidor.Entities.ReservaActividad;
import com.tictok.RUServidor.Entities.ReservaCancha;

import java.util.ArrayList;
import java.util.List;

public class ReservaMapper {

    public static ReservaDTO fromReservaCanchaToReservaDTO(ReservaCancha reservaCancha){
        int cedula = reservaCancha.getUsuario().getCedula();
        String nombreCentro = reservaCancha.getCancha().getCanchaId().getCentroDeportivo();
        String nombreActividad = reservaCancha.getCancha().getCanchaId().getNombreServicio();
        String tipo = "Cancha";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(reservaCancha.getCancha().getCanchaId());
        Long codigoReserva = reservaCancha.getId();
        return new ReservaDTO(cedula, nombreCentro, nombreActividad, tipo, horario, codigoReserva);
    }

    public static ReservaDTO fromReservaActividadToReservaDTO(ReservaActividad reservaActividad) {
        int cedula = reservaActividad.getUsuario().getCedula();
        String nombreCentro = reservaActividad.getActividad().getActividadId().getCentroDeportivo();
        String nombreActividad = reservaActividad.getActividad().getActividadId().getNombreServicio();
        String tipo = "Actividad";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(reservaActividad.getActividad().getActividadId());
        Long codigoReserva = reservaActividad.getId();
        return new ReservaDTO(cedula, nombreCentro, nombreActividad, tipo, horario, codigoReserva);
    }

    // TODO Arreglar todo esto que me tiene triste
    public static Reserva2DTO fromReservaCanchaToReserva2DTO(ReservaCancha reservaCancha){
        String mail = reservaCancha.getUsuario().getCuenta().getMail();
        String nombreCentro = reservaCancha.getCancha().getCanchaId().getCentroDeportivo();
        String nombreActividad = reservaCancha.getCancha().getCanchaId().getNombreServicio();
        String tipo = "Cancha";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(reservaCancha.getCancha().getCanchaId());
        Long codigoReserva = reservaCancha.getId();
        Long codigoReservaPadre;
        try {
            codigoReservaPadre = reservaCancha.getReservaCanchaPadre().getId();
        } catch (NullPointerException n){
            codigoReservaPadre = null;
        }
        String fecha = reservaCancha.getFecha().toString();
        return new Reserva2DTO(mail, nombreCentro, nombreActividad, tipo, horario, codigoReserva, codigoReservaPadre, fecha);
    }

    public static Reserva2DTO fromReservaActividadToReserva2DTO(ReservaActividad reservaActividad){
        String mail = reservaActividad.getUsuario().getCuenta().getMail();
        String nombreCentro = reservaActividad.getActividad().getActividadId().getCentroDeportivo();
        String nombreActividad = reservaActividad.getActividad().getActividadId().getNombreServicio();
        String tipo = "Actividad";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(reservaActividad.getActividad().getActividadId());
        Long codigoReserva = reservaActividad.getId();
        String fecha = reservaActividad.getFecha().toString();
        return new Reserva2DTO(mail, nombreCentro, nombreActividad, tipo, horario, codigoReserva, fecha);
    }
    public static List<Reserva2DTO> fromListReservasToReserva2DTO(List<ReservaCancha> reservaCanchaList,
                                                                  List<ReservaActividad> reservaActividadList){
        List<Reserva2DTO> reserva2DTOList = new ArrayList<Reserva2DTO>(reservaCanchaList.size() + reservaActividadList.size());
        for (int i = 0; i<reservaCanchaList.size(); i++){
            reserva2DTOList.add(fromReservaCanchaToReserva2DTO(reservaCanchaList.get(i)));
        }
        for (int i = 0; i<reservaActividadList.size(); i++){
            reserva2DTOList.add(fromReservaActividadToReserva2DTO(reservaActividadList.get(i)));
        }
        return reserva2DTOList;
    }

    public static List<Reserva2DTO> fromListReservaCanchaToReserva2DTO(List<ReservaCancha> reservaCanchaList) {
        List<Reserva2DTO> reserva2DTOList = new ArrayList<Reserva2DTO>(reservaCanchaList.size());
        for (int i = 0; i<reservaCanchaList.size(); i++){
            reserva2DTOList.add(fromReservaCanchaToReserva2DTO(reservaCanchaList.get(i)));
        }
        return reserva2DTOList;
    }
}
