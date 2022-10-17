package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.MegaUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRest {

    @Autowired
    private MiniCuenta miniCuenta;

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
            HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario/"+miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .body(megaUsuarioJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> buscarDatosUsuarioFromMail(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/cuenta/mail?mail="+ miniCuenta.getMailMiniCuenta())
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
//            ReservaDTO reservarDTO = new ReservaDTO(mailUsuario,nombreCentro,nombreActividad,horario);
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
