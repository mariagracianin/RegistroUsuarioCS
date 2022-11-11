package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.CheckInDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUServidor.Entities.CheckInActividad;
import com.tictok.RUServidor.Entities.CheckInCancha;
import com.tictok.RUServidor.Entities.ReservaActividad;
import com.tictok.RUServidor.Entities.ReservaCancha;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static com.tictok.RUServidor.Mappers.ReservaMapper.fromReservaCanchaToReservaDTO;

public class CheckInMapper {
    public static CheckInDTO fromCheckInCanchaToCheckInDTO(CheckInCancha checkInCancha){
        Integer cedula = checkInCancha.getUsuario().getCedula();
        String nombreCentro = checkInCancha.getCancha().getCanchaId().getCentroDeportivo();
        String nombreActividad = checkInCancha.getCancha().getCanchaId().getNombreServicio();
        String tipo = "Cancha";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(checkInCancha.getCancha().getCanchaId());
        Long codigoCheckin = checkInCancha.getId();
        Long codigoReservaPadre;
        Double precio = checkInCancha.getCancha().getPrecio();
        try {
            codigoReservaPadre = checkInCancha.getReservaCanchaPadre().getId();
        } catch (NullPointerException n){
            codigoReservaPadre = null;
        }
        String fecha = checkInCancha.getFecha().toString();
        return new CheckInDTO(cedula,nombreCentro,nombreActividad,tipo,horario,codigoCheckin,fecha,precio);
    }

    public static CheckInDTO fromCheckInActividadToCheckInDTO(CheckInActividad checkInActividad){
        int cedula = checkInActividad.getUsuario().getCedula();
        String nombreCentro = checkInActividad.getActividad().getActividadId().getCentroDeportivo();
        String nombreActividad = checkInActividad.getActividad().getActividadId().getNombreServicio();
        String tipo = "Actividad";
        HorarioDTO horario = HorarioMapper.fromServicioIdtoHorarioDTO(checkInActividad.getActividad().getActividadId());
        Long codigoCheckIn = checkInActividad.getId();
        String fecha = checkInActividad.getFecha().toString();
        Double precio = checkInActividad.getActividad().getPrecio();
        return new CheckInDTO(cedula,nombreCentro,nombreActividad,tipo,horario,codigoCheckIn,fecha,precio);
    }

    public static List<CheckInDTO> fromListsCheckInsToCheckInDTO(List<CheckInCancha> checkCanchaList,
                                                                 List<CheckInActividad> checkActividadList){
        List<CheckInDTO> checkInDTOList = new ArrayList<CheckInDTO>(checkCanchaList.size()+checkActividadList.size());
        for (int i = 0; i<checkCanchaList.size(); i++){
            checkInDTOList.add(fromCheckInCanchaToCheckInDTO(checkCanchaList.get(i)));
        }
        for (int i = 0; i<checkActividadList.size(); i++){
            checkInDTOList.add(fromCheckInActividadToCheckInDTO(checkActividadList.get(i)));
        }
        return checkInDTOList;
    }

    public static List<CheckInDTO> fromListsCheckInsCanchasToCheckInDTO(List<CheckInCancha> checkCanchaList){
        List<CheckInDTO> checkInDTOList = new ArrayList<CheckInDTO>(checkCanchaList.size());
        for (int i = 0; i<checkCanchaList.size(); i++){
            checkInDTOList.add(fromCheckInCanchaToCheckInDTO(checkCanchaList.get(i)));
        }
        return checkInDTOList;
    }

    public static List<CheckInDTO> fromListsCheckInsActividadToCheckInDTO(List<CheckInActividad> checkActividadList){
        List<CheckInDTO> checkInDTOList = new ArrayList<CheckInDTO>(checkActividadList.size());
        for (int i = 0; i<checkActividadList.size(); i++){
            checkInDTOList.add(fromCheckInActividadToCheckInDTO(checkActividadList.get(i)));
        }
        return checkInDTOList;
    }
}
