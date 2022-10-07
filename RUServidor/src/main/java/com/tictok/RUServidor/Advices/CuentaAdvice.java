package com.tictok.RUServidor.Advices;


import com.tictok.RUServidor.Exceptions.CuentaNoExisteException;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Exceptions.PasswordDoesNotMatchException;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CuentaAdvice {

    //TODO Revisar el mensaje de error
    @ResponseBody()
    @ExceptionHandler(PasswordDoesNotMatchException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String PasswordDoesNotMatchAdvice(PasswordDoesNotMatchException ex) {
        return ex.getMessage();
    }

    @ResponseBody()
    @ExceptionHandler(CuentaNoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CuentaNoExisteAdvice(CuentaNoExisteException ex) {
        return ex.getMessage();
    }

    @ResponseBody()
    @ExceptionHandler(CuentaYaExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String CuentaYaExisteAdvice(CuentaYaExisteException ex) {
        return ex.getMessage();
    }
}
