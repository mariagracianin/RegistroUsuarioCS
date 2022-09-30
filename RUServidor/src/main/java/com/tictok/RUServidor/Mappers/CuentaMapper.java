package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;

public class CuentaMapper {
    public static CuentaDTO toCuentaDTO(Cuenta cuenta) {

        return new CuentaDTO(cuenta.getId(), cuenta.getPassword(),
                cuenta.getTipo());

    }

    public static Cuenta toCuenta(CuentaDTO cuentaDTO) {
        return new Cuenta(cuentaDTO.getMail(), cuentaDTO.getPassword(), cuentaDTO.getTipo());
    }

    public static Cuenta toCuentaFromMegaUsuarioDTO(MegaUsuarioDTO megauserDTO){
        if(megauserDTO == null){
            return null;
        }
        return new Cuenta(megauserDTO.getCuentaMail(),megauserDTO.getPassword(),"user");
    }
}
