package com.tictok.RUServidor.Usuario;

public class UsuarioYaExisteException extends RuntimeException {
    UsuarioYaExisteException(String telefono) {
        super("Ya existe el usuario con telefono" + telefono);
        }
}
