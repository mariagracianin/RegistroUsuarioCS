package com.tictok.RUServidor.Mappers;

//import com.tictok.Commons.ServicioIdDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.NotTables.ServicioIdSinHorario;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ActividadMapper {
    public static List<SuperActividadDTO> fromActividadesListToSuperActividadDTOList(List<Actividad> actividadList) {
        Hashtable<ServicioIdSinHorario,SuperActividadDTO> hashDeActividades = new Hashtable<ServicioIdSinHorario, SuperActividadDTO>();
        Actividad actividadNueva;
        ServicioIdSinHorario servicioIdSinHorario;
        SuperActividadDTO superActividadDTOtemporal;

        for (int i=0; i<actividadList.size(); i++){
            actividadNueva = actividadList.get(i);
            servicioIdSinHorario = new ServicioIdSinHorario(actividadNueva.getActividadId().getNombreServicio(),
                    actividadNueva.getActividadId().getCentroDeportivo());
            if (hashDeActividades.containsKey(servicioIdSinHorario)){
                superActividadDTOtemporal = hashDeActividades.get(servicioIdSinHorario);
                HorarioDTO horarioDTO = HorarioMapper.fromServicioIdtoHorarioDTO(actividadNueva.getActividadId());
                superActividadDTOtemporal.addHorario(horarioDTO);
            }
            else{
                superActividadDTOtemporal = new SuperActividadDTO(servicioIdSinHorario.getNombreServicio(), servicioIdSinHorario.getCentroDeportivo(),
                        actividadNueva.getPrecio(), actividadNueva.getPaseLibre(), actividadNueva.getCentroDeportivo().getAddress(),
                        actividadNueva.getCentroDeportivo().getBarrio(), actividadNueva.getCentroDeportivo().getTelefono(),
                        "imageSource", new ArrayList<HorarioDTO>());
                HorarioDTO horarioDTO = HorarioMapper.fromServicioIdtoHorarioDTO(actividadNueva.getActividadId());
                superActividadDTOtemporal.addHorario(horarioDTO);
                hashDeActividades.put(servicioIdSinHorario, superActividadDTOtemporal);
            }

        }
        return hashDeActividades.values().stream().toList();
    }

//    public static SuperActividadDTO toSuperActividadDTO(List<Actividad> actividades){
//        if (actividades.size() == 0){return null;}
//
//        List<HorarioDTO> serviciosIDsArray = new ArrayList<>(actividades.size());
//
//        for(int i=0; i<actividades.size(); i++){
//            Actividad actividadI = actividades.get(i);
//            ServicioId servicioI = actividadI.getActividadId();
//            HorarioDTO servicioIdDTOI;
//
//            if(actividadI.getPaseLibre()){
//                servicioIdDTOI = new ServicioIdDTO(servicioI.getNombreServicio(),servicioI.getCentroDeportivo(),"","","");
//            }else {
//                servicioIdDTOI = new ServicioIdDTO(servicioI.getNombreServicio(),servicioI.getCentroDeportivo(),servicioI.getDia().toString(),servicioI.getHoraInicio().toString(),servicioI.getHoraFin().toString());
//            }
//            serviciosIDsArray.add(i,servicioIdDTOI);
//        }
//
//        return new SuperActividadDTO(actividades.get(0).getActividadId().getNombreServicio(),actividades.get(0).getPrecio(),actividades.get(0).getPaseLibre(),actividades.get(0).getCentroDeportivo().getNombreCentro(),actividades.get(0).getCentroDeportivo().getAddress(),actividades.get(0).getCentroDeportivo().getBarrio(),actividades.get(0).getCentroDeportivo().getTelefono(),"fotito", serviciosIDsArray);
//    }
}
