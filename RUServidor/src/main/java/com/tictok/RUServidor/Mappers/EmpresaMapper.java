package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;

public class EmpresaMapper {

    public static Empresa toEmpresaFromNuevaEmpresaDTO(NuevaEmpresaDTO nuevaEmpresaDTO){
        if(nuevaEmpresaDTO == null){
            return null;
        }
        return new Empresa(nuevaEmpresaDTO.getNombreEmpresa(),nuevaEmpresaDTO.getAdress(),nuevaEmpresaDTO.getTelefono(), nuevaEmpresaDTO.getEncargado(), nuevaEmpresaDTO.getRut(), nuevaEmpresaDTO.getRazonSocial());
    }
}
