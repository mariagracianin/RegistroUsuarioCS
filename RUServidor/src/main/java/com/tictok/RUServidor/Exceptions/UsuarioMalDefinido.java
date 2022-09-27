package com.tictok.RUServidor.Exceptions;

public class UsuarioMalDefinido extends RuntimeException {
    public UsuarioMalDefinido() {
        super("El telefono es incorrecto");
    }
}
