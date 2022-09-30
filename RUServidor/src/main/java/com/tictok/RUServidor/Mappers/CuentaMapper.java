package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;

public class CuentaMapper {
    public static Object toCuentaDTO(Cuenta cuenta) {

        return new CuentaDTO(cuenta.getId(), cuenta.getPassword(),
                cuenta.getTipo());

    }

    public static Cuenta toCuenta(CuentaDTO cuentaDTO) {
        return new Cuenta(cuentaDTO.getMail(), cuentaDTO.getPassword(), cuentaDTO.getTipo());
    }
}
