package com.tictok.RUServidor.Usuario.ErrorHandling;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String telefono) {
        super("Ya existe el usuario con telefono" + telefono);
        }
}
