package com.tictok.RUCliente;

import org.springframework.stereotype.Component;

@Component
public class MiniCuenta {
    private String mailMiniCuenta;
    private String tipoMiniCuenta;

    public MiniCuenta() {
    }

    public String getMailMiniCuenta() {
        return mailMiniCuenta;
    }

    public void setMailMiniCuenta(String mailMiniCuenta) {
        this.mailMiniCuenta = mailMiniCuenta;
    }

    public String getTipoMiniCuenta() {
        return tipoMiniCuenta;
    }

    public void setTipoMiniCuenta(String tipoMiniCuenta) {
        this.tipoMiniCuenta = tipoMiniCuenta;
    }
}
