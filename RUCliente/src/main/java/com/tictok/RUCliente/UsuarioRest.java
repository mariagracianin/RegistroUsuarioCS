package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioRest {

    public HttpResponse<JsonNode> guardarUsuario(String mail,String password, int cedula, String vencCarne, String nombres, String apellidos, String telefono, double saldoBase,  double sobregiro) {
        String usuarioJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            MegaUsuarioDTO megausuarioDTO = new MegaUsuarioDTO(mail,password, cedula, vencCarne, nombres, apellidos, telefono, saldoBase, sobregiro, saldoBase);
            usuarioJSON = jsonObjectMapper.writeValueAsString(megausuarioDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(usuarioJSON)
                    .asJson();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<JsonNode> obtenerUsuariosFromEmpresaX(String nombreEmpresa){
        try {
            HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/usuario/empresa{"+nombreEmpresa+"}/all")
                    .header("Content-Type", "application/json")
                    .asJson();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
