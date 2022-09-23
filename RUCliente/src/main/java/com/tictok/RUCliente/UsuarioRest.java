package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRest {

    public int guardarUsuario(String nombre, String direc, String telefono) {
        String usuarioJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioJSON = jsonObjectMapper.writeValueAsString(usuarioDTO);
        }catch (Exception e){
            throw new RuntimeException();
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(usuarioJSON)
                    .asString();
        }catch (Exception e){
            throw new RuntimeException();
        }



        return 0;
    }

    public int guardarUsuario2(String nombre, String direc, String telefono) {
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
