package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioRest {

    public int guardarUsuario(String mail, String password, int cedula, String vencCarne, String nombres, String apellidos, String telefono, double saldoBase,  double sobregiro) {
        String usuarioJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            UsuarioDTO usuarioDTO = new UsuarioDTO(mail, password, cedula, vencCarne, nombres, apellidos, telefono, saldoBase, sobregiro, 0);
            usuarioJSON = jsonObjectMapper.writeValueAsString(usuarioDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(usuarioJSON).asJson();
            return response.getCode();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void obtenerUsuarios(String nombreEmpresa){


    }

}
