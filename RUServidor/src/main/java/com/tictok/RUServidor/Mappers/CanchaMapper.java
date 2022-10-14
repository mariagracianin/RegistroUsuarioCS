package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperChanchaDTO;
import com.tictok.RUServidor.Entities.Cancha;

import java.util.List;

public class CanchaMapper {
    public static SuperChanchaDTO toSuperChanchaDTO(Cancha cancha){
        List<String> horariosDTO = null;
        for(int i=0; i<cancha.getHorarios().size(); i++){
            horariosDTO.add(cancha.getHorarios().get(i).getHora().toString());
        }
        return new SuperChanchaDTO(cancha.getNombreServicio(),cancha.getPrecio(),cancha.getCupos(),cancha.getCentroDeportivo().getNombreCentro(),cancha.getCentroDeportivo().getAddress(),cancha.getCentroDeportivo().getBarrio(),cancha.getCentroDeportivo().getTelefono(),horariosDTO);
    }
}
