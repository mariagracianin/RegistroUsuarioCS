package com.tictok.RUServidor.Mappers;
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
        Hashtable<ServicioIdSinHorario, SuperActividadDTO> hashDeActividades = new Hashtable<ServicioIdSinHorario, SuperActividadDTO>();
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
        Double precio;
        Boolean paseLibre;
        String address;
        String barrio;
        String telefono;
        String imageString;
        for (int i = 0; i< actividadList.size(); i++){
            actividadInfo = actividadList.get(i);
            nombreServicio = actividadInfo.getNombreActividad();
            nombreCentro = actividadInfo.getNombreCentro();
            precio = actividadInfo.getPrecio();
            paseLibre = actividadInfo.getPaseLibre();
            address = actividadInfo.getAddress();
            barrio = actividadInfo.getBarrio();
            telefono = actividadInfo.getTelefono();
            imageString = actividadInfo.getImageString();

            SuperActividadDTO superActividad = new SuperActividadDTO(nombreServicio, nombreCentro, precio,
                                    paseLibre, address, barrio, telefono, imageString);
            superActividadDTOList.add(superActividad);
        }

        return superActividadDTOList;
    }

    public static List<SuperActividadDTO> fromQueryResultListToSuperActividadDTOList(List<Object[]> actividadInfosObjects) {
        List<SuperActividadDTO> superActividadDTOList = new ArrayList<SuperActividadDTO>(actividadInfosObjects.size());
        SuperActividadDTO superActividadDTO;
        for (int i = 0; i<actividadInfosObjects.size(); i++){
            superActividadDTO = SuperActividadDTO.map(SuperActividadDTO.class, actividadInfosObjects.get(i));
            superActividadDTOList.add(superActividadDTO);
        }
        return superActividadDTOList;
    }

//    private static SuperActividadDTO fromQueryResultToSuperActividadDTO(Object[] actividadInfoObject){
//        System.out.println("-------------------------");
//        System.out.println("Nuevo Centro");
//        System.out.println("-------------------------");
//        for (int i=0; i<actividadInfoObject.length; i++){
//            System.out.println(actividadInfoObject[i]);
//        }
//        if (actividadInfoObject.length == 9){
//            return new SuperActividadDTO((String)actividadInfoObject[1], (String)actividadInfoObject[0], (Double) actividadInfoObject[4], (Boolean) actividadInfoObject[3],
//                    (String) actividadInfoObject[6], (String) actividadInfoObject[7], (String) actividadInfoObject[8], null);
//        } else{
//            return new SuperActividadDTO((String)actividadInfoObject[1], (String)actividadInfoObject[0], (Double) actividadInfoObject[4], (Boolean) actividadInfoObject[3],
//                    (String) actividadInfoObject[6], (String) actividadInfoObject[7], (String) actividadInfoObject[8], (String) actividadInfoObject[9]);
//        }
//    }
}
