package com.tictok.RUServidor.Exceptions;

public class UsuarioMalDefinidoException extends RuntimeException {
    public UsuarioMalDefinidoException() {
        super("El telefono es incorrecto");
    }
}
