package com.tictok.RUCliente;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;


@Component
public class UsuarioRest {

    //void o nos da el 200ok 300 etc?
    public void guardarUsuario(String nombre, String direc, String telefono) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"nombre\": \""+ nombre + "\",\r\n  \"direc\": \" " + direc + "\",\r\n  \"telefono\": \" " + telefono + "\"\r\n}")
                .asString();
    }

}
