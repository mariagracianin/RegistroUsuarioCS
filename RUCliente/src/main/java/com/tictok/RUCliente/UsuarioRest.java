package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.ReservarDTO;
import com.tictok.Commons.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioRest {

    public HttpResponse<String> guardarUsuario(String mail, String password, int cedula, String vencCarne, String nombres, String apellidos,
                                               String telefono, double saldoBase,  double sobregiro, String address) {
        String megaUsuarioJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            MegaUsuarioDTO megaUsuarioDTO = new MegaUsuarioDTO(mail,password, cedula, vencCarne, nombres, apellidos, telefono, saldoBase, sobregiro, saldoBase, address);
            megaUsuarioJSON = jsonObjectMapper.writeValueAsString(megaUsuarioDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario")
                    .header("Content-Type", "application/json")
                    .body(megaUsuarioJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerUsuariosFromEmpresaX(String nombreEmpresa){
        //nombreEmpresa es con la que entre a la app
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/empresa/"+ "empresa" +"/usuarios")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> buscarDatosUsuarioFromMail(String mail){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/cuenta/mail?mail="+ mail)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //NO FUNCIONA SEGURO :(
//    public HttpResponse<String> hacerReserva(String mailUsuario, String nombreCentro, String nombreActividad, HorarioDTO horario){
//        String reservarJSON = "";
//        try {
//            ObjectMapper jsonObjectMapper = new ObjectMapper();
//            ReservarDTO reservarDTO = new ReservarDTO(mailUsuario,nombreCentro,nombreActividad,horario);
//            reservarJSON = jsonObjectMapper.writeValueAsString(reservarDTO);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//
//        try {
//            HttpResponse<String> response = Unirest.post("http://localhost:8080/NO SE AUN")
//                    .header("Content-Type", "application/json")
//                    .body(reservarJSON)
//                    .asString();
//            return response;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//
//    }

}
