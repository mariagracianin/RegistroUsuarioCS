package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoginRest {

    public HttpResponse<String> autenticar(String mail, String password) {
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/cuenta/autenticar?mail=admin&password=contra")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
