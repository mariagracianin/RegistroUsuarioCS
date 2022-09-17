package com.tictok.RUServidor.Usuario.ErrorHandling;

public class UsuarioMalDefinido extends RuntimeException {
    public UsuarioMalDefinido() {
        super("El telefono es incorrecto");
    }
}
