package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.Commons.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioDTO userDTO){

        if(userDTO == null){
            return null;
        }
        return new Usuario(userDTO.getCedula(), userDTO.getVencimientoCarne(),userDTO.getNombre(),
                userDTO.getApellido(), userDTO.getTelefono(),userDTO.getSaldoBase(),userDTO.getSobregiro(),userDTO.getSaldo(), userDTO.getAddress());
    }

    public static UsuarioDTO toUsuarioDTO(Usuario user){

        if(user == null){
            return null;
        }

        return new UsuarioDTO(user.getStringCuenta(), user.getCedula(),user.getVencimientoCarne(),user.getNombre(),user.getApellido(),user.getTelefono()
                ,user.getSaldoBase(),user.getSobregiro(),user.getSaldo(), user.getAddress());
    }

    public static MegaUsuarioDTO toMegaUsuarioDTO(Usuario user){

        if(user == null){
            return null;
        }

        return new MegaUsuarioDTO(user.getStringCuenta(),user.getCuenta().getPassword(), user.getCedula(),user.getVencimientoCarne(),user.getNombre(),user.getApellido(),user.getTelefono()
                ,user.getSaldoBase(),user.getSobregiro(),user.getSaldo(), user.getAddress());
    }

    public static Usuario toUsuarioFromMegaUsuarioDTO(MegaUsuarioDTO megauserDTO){
        if(megauserDTO == null){
            return null;
        }
        return new Usuario(megauserDTO.getCedula(), megauserDTO.getVencimientoCarne(),megauserDTO.getNombre(),
                megauserDTO.getApellido(), megauserDTO.getTelefono(),megauserDTO.getSaldoBase(),megauserDTO.getSobregiro(),
                megauserDTO.getSaldo(), megauserDTO.getAddress());

    }

    //
    public static List<UsuarioDTO> toUsuarioDTOList(List<Usuario> usuariosList){
        List usuarioDTOList = new ArrayList<UsuarioDTO>(usuariosList.size());
        for (int i = 0; i<usuariosList.size(); i++) {
            usuarioDTOList.add(UsuarioMapper.toUsuarioDTO((Usuario) usuariosList.get(i)));
        }
        return usuarioDTOList;
    }
    public static Usuario toUpdateUsuario(Usuario user, Usuario updateUser){
        //?
        return null;
    }
}