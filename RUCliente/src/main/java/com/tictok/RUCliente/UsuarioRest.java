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

    public HttpResponse<String> guardarUsuario(String mail, String password, int cedula, String vencCarne, String nombres, String apellidos, String telefono, double saldoBase,  double sobregiro) {
        String usuarioJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            UsuarioDTO usuarioDTO = new UsuarioDTO(mail, cedula, vencCarne, nombres, apellidos, telefono, saldoBase, sobregiro, saldoBase);
            usuarioJSON = jsonObjectMapper.writeValueAsString(usuarioDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(usuarioJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerUsuariosFromEmpresaX(String nombreEmpresa){
        //"http://localhost:8080/usuario/empresa{"+nombreEmpresa+"}/all"
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/usuario/all")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
