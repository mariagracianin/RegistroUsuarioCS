package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoginRest {

    /*public HttpResponse<String> autenticar(String mail, String password) {
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/cuenta/autenticar?mail="+mail+"&password="+ password)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    public HttpResponse<String> autenticarBien(String mail, String password){
        String cuentaJSON = "";

        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            CuentaDTO cuentaDTO = new CuentaDTO(mail,password,"");
            cuentaJSON = jsonObjectMapper.writeValueAsString(cuentaDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/cuenta/autenticar")
                    .header("Content-Type", "application/json")
                    .body(cuentaJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
