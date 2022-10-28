package com.tictok.RUServidor.Mappers;
import com.tictok.Commons.ActividadConHorariosYCuposDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.NotTables.ServicioIdSinHorario;
import com.tictok.RUServidor.Projections.ActividadInfo;

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
                String imagenString;
                try {
                    imagenString = actividadNueva.getImagen().getImagenString();
                } catch (NullPointerException e){
                    imagenString = null;
                }
                superActividadDTOtemporal = new SuperActividadDTO(servicioIdSinHorario.getNombreServicio(), servicioIdSinHorario.getCentroDeportivo(),
                        actividadNueva.getPrecio(), actividadNueva.getPaseLibre(), actividadNueva.getCentroDeportivo().getAddress(),
                        actividadNueva.getCentroDeportivo().getBarrio(), actividadNueva.getCentroDeportivo().getTelefono(),
                        imagenString, new ArrayList<HorarioDTO>());
                HorarioDTO horarioDTO = HorarioMapper.fromServicioIdtoHorarioDTO(actividadNueva.getActividadId());
                superActividadDTOtemporal.addHorario(horarioDTO);
                hashDeActividades.put(servicioIdSinHorario, superActividadDTOtemporal);
            }
        }
        return hashDeActividades.values().stream().toList();
    }

    public static List<SuperActividadDTO> fromActividadesInfoListToSuperActividadDTOList(List<ActividadInfo> actividadList){
        List<SuperActividadDTO> superActividadDTOList = new ArrayList<SuperActividadDTO>(actividadList.size());
        ActividadInfo actividadInfo;
        String nombreServicio;
        String nombreCentro;
        Integer precio;
        Boolean paseLibre;
        String address;
        String barrio;
        String telefono;
        String imageString;
        for (int i = 0; i< actividadList.size(); i++){
            actividadInfo = actividadList.get(i);
            nombreServicio = actividadInfo.getActividadId().getNombreServicio();
            nombreCentro = actividadInfo.getActividadId().getCentroDeportivo();
            precio = actividadInfo.getPrecio();
            paseLibre = actividadInfo.isPaseLibre();
            address = actividadInfo.getCentroDeportivo().getAddress();
            barrio = actividadInfo.getCentroDeportivo().getBarrio();
            telefono = actividadInfo.getCentroDeportivo().getTelefono();
            imageString = actividadInfo.getImagen().getImagenString();

            SuperActividadDTO superActividad = new SuperActividadDTO(nombreServicio, nombreCentro, precio,
                                    paseLibre, address, barrio, telefono, imageString);
            superActividadDTOList.add(superActividad);
        }

        return superActividadDTOList;
    }

}
