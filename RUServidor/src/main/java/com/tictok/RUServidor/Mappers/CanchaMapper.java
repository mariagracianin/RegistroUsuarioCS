package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioIdSinHorario;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CanchaMapper {
    public static List<SuperCanchaDTO> fromCanchaListToSuperCanchaDTOList(List<Cancha> canchaList) {
        Hashtable<ServicioIdSinHorario, SuperCanchaDTO> hashDeCanchas = new Hashtable<ServicioIdSinHorario, SuperCanchaDTO>();
        Cancha canchaNueva;
        ServicioIdSinHorario servicioIdSinHorario;
        SuperCanchaDTO superCanchaDTOtemporal;


        for (int i=0; i<canchaList.size(); i++){
            canchaNueva = canchaList.get(i);
            servicioIdSinHorario = new ServicioIdSinHorario(canchaNueva.getCanchaId().getNombreServicio(),
                    canchaNueva.getCanchaId().getCentroDeportivo());
            if (hashDeCanchas.containsKey(servicioIdSinHorario)){
                superCanchaDTOtemporal = hashDeCanchas.get(servicioIdSinHorario);
                HorarioDTO horarioDTO = HorarioMapper.fromServicioIdtoHorarioDTO(canchaNueva.getCanchaId());
                superCanchaDTOtemporal.addHorario(horarioDTO);
            }
            else{
                String imagenString;
                try {
                    imagenString = canchaNueva.getImagen().getImagenString();
                } catch (NullPointerException e) {
                    imagenString = null;
                }
                superCanchaDTOtemporal = new SuperCanchaDTO(servicioIdSinHorario.getNombreServicio(), servicioIdSinHorario.getCentroDeportivo(),
                        canchaNueva.getPrecio(), canchaNueva.getCentroDeportivo().getAddress(), canchaNueva.getCentroDeportivo().getBarrio(),
                        canchaNueva.getCentroDeportivo().getTelefono(), imagenString, new ArrayList<HorarioDTO>());
            }
            HorarioDTO horarioDTO = HorarioMapper.fromServicioIdtoHorarioDTO(canchaNueva.getCanchaId());
            superCanchaDTOtemporal.addHorario(horarioDTO);
            hashDeCanchas.put(servicioIdSinHorario, superCanchaDTOtemporal);
        }
        return hashDeCanchas.values().stream().toList();
    }




//    public static SuperChanchaDTO toSuperChanchaDTO(Cancha cancha){
//        /*List<HorarioDTO> horariosDTO = null;
//        /*for(int i=0; i<cancha.getHorarios().size(); i++){
//            //hacer que la lista sea de horarios dto
//        }*/
//        //return new SuperChanchaDTO(cancha.getNombreServicio(),cancha.getPrecio(),cancha.getCupos(),cancha.getCentroDeportivo().getNombreCentro(),cancha.getCentroDeportivo().getAddress(),cancha.getCentroDeportivo().getBarrio(),cancha.getCentroDeportivo().getTelefono(),horariosDTO);
//        return null;
//    }

}
