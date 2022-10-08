package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.CentroDeportivo;

public class CentroMapper {

    public static CentroDeportivo toCentroFromNuevoCentroDTO(NuevoCentroDTO nuevoCentroDTO){
        if(nuevoCentroDTO == null){
            return null;
        }
        return new CentroDeportivo(nuevoCentroDTO.getNombreCentro(),nuevoCentroDTO.getAdress(),nuevoCentroDTO.getTelefono(),nuevoCentroDTO.getEncargado());
    }
}
