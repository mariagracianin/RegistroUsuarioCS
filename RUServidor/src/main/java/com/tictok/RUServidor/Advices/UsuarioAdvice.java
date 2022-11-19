package com.tictok.RUServidor.Advices;

import com.tictok.RUServidor.Exceptions.CarneVencido;
import com.tictok.RUServidor.Exceptions.SaldoInsuficienteException;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsuarioAdvice {

    @ResponseBody()
    @ExceptionHandler(UsuarioYaExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String usuarioYaExisteHandler(UsuarioYaExisteException ex) {
        return ex.getMessage();
    }

    @ResponseBody()
    @ExceptionHandler(UsuarioNoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String usuarioNoExisteHandler(UsuarioNoExisteException ex){
        return ex.getMessage();
    }

    @ResponseBody()
    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String saldoInsuficienteHandler(SaldoInsuficienteException ex) {
        return "El saldo es insuficiente para unirse a este servicio";
    }

    @ResponseBody()
    @ExceptionHandler(CarneVencido.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String carneVencidoHandler(CarneVencido ex){
        return "El carné de salud está vencido, no puede unirse a este servicio";
    }
}
