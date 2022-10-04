package com.tictok.RUServidor.Exceptions;

public class UsuarioNoExisteException extends Exception{
    public UsuarioNoExisteException(int cedula) {
        super("El usuario con cedula " + cedula + " no existe" );
    }


}
