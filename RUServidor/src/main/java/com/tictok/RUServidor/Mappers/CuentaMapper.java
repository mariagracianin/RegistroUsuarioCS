package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;

public class CuentaMapper {
    public static CuentaDTO toCuentaDTO(Cuenta cuenta) {
        return new CuentaDTO(cuenta.getId(), cuenta.getPassword(),
                cuenta.getTipo());

    }

    public static MiniCuentaDTO toMiniCuentaDTO(Cuenta cuenta){
        return new MiniCuentaDTO(cuenta.getMail(), cuenta.getTipo());
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

    public static Cuenta toCuentaFromNuevaEmpresaDTO(NuevaEmpresaDTO nuevaEmpresaDTO){
        if(nuevaEmpresaDTO == null){
            return null;
        }
        return new Cuenta(nuevaEmpresaDTO.getMail(),nuevaEmpresaDTO.getPassword(),"empresa");
    }

    public static Cuenta toCuentaFromNuevoCentroDTO(NuevoCentroDTO nuevoCentroDTO){
        if(nuevoCentroDTO == null){
            return null;
        }
        return new Cuenta(nuevoCentroDTO.getMail(),nuevoCentroDTO.getPassword(),"centro");
    }

}
