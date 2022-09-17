package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;


@Component
public class UsuarioRest {

    public int guardarUsuario(String nombre, String direc, String telefono) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode rest = mapper.createObjectNode();
            rest.put("nombre", nombre);
            rest.put("direc", direc);
            rest.put("telefono", telefono);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
            return response.getCode();

        } catch (Exception t) {
            throw new RuntimeException(t);
        }
    }

}
