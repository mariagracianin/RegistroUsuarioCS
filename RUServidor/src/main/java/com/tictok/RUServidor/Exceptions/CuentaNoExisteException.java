package com.tictok.RUServidor.Exceptions;

public class CuentaNoExisteException extends Exception{
    public CuentaNoExisteException(String mail) {
        super("La cuenta con mail " + mail + " no existe" );
    }
}
