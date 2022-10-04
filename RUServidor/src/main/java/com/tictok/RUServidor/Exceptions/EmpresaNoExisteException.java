package com.tictok.RUServidor.Exceptions;

public class EmpresaNoExisteException extends Exception{
    public EmpresaNoExisteException(String nombreEmpresa){
        super("La empresa " + nombreEmpresa + " no existe.");
    }
}
