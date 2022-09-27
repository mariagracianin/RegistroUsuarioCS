package com.tictok.RUServidor.Advices;

import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsuarioYaExisteAdvice {


    @ResponseBody()
    @ExceptionHandler(UsuarioYaExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String usuarioNotFoundHandler(UsuarioYaExisteException ex) {
        return ex.getMessage();
    }
}
