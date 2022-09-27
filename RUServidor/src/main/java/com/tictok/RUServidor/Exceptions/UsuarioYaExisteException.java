package com.tictok.RUServidor.Exceptions;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String telefono) {
        super("Ya existe el usuario con telefono " + telefono);
        }
}
