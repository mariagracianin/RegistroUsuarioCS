package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ServicioIdDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;

import java.util.ArrayList;
import java.util.List;

public class ActividadMapper {

    public static SuperActividadDTO toSuperActividadDTO(List<Actividad> actividades){
        if (actividades.size() == 0){return null;}

        List<HorarioDTO> horariosDTOArray = new ArrayList<>(actividades.size());

        for(int i=0; i<actividades.size(); i++){
            Actividad actividadI = actividades.get(i);
            ServicioId servicioI = actividadI.getActividadId();
            HorarioDTO horarioDTOI;

            if(actividadI.getPaseLibre()){
                horarioDTOI = new HorarioDTO(0,0,0);
            }else {
                horarioDTOI = new HorarioDTO(servicioI.getDia().getValue(),servicioI.getHoraInicio().getHour(),servicioI.getHoraFin().getHour());
            }
            horariosDTOArray.add(i,horarioDTOI);
        }

        return new SuperActividadDTO(actividades.get(0).getActividadId().getNombreServicio(),actividades.get(0).getPrecio(),actividades.get(0).getPaseLibre(),actividades.get(0).getCentroDeportivo().getNombreCentro(),actividades.get(0).getCentroDeportivo().getAddress(),actividades.get(0).getCentroDeportivo().getBarrio(),actividades.get(0).getCentroDeportivo().getTelefono(),"fotito", horariosDTOArray);
    }
}
