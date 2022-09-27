package com.tictok.RUServidor.Mappers;

import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.Commons.UsuarioDTO;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioDTO userDTO){

        if(userDTO != null){
            return null;
        }
        return new Usuario(userDTO.getMail(),userDTO.getPassword(),userDTO.getCedula(),userDTO.getVencimientoCarne(),userDTO.getNombre(),userDTO.getApellido(),userDTO.getTelefono(),userDTO.getSaldoBase(),userDTO.getSobregiro(),userDTO.getSaldo());
    }

    public static UsuarioDTO toUsuarioDTO(UsuarioDTO user){

        if(user != null){
            return null;
        }

        return new UsuarioDTO(user.getMail(),user.getPassword(),user.getCedula(),user.getVencimientoCarne(),user.getNombre(),user.getApellido(),user.getTelefono(),user.getSaldoBase(),user.getSobregiro(),user.getSaldo());
    }

    public static Usuario toUpdateUsuario(Usuario user, Usuario updateUser){
        //?
        return null;
    }
}
