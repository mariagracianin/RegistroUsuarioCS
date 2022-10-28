package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.Reserva2DTO;
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

    public HttpResponse<String> buscarDatosFromUsuarioLogeado(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/cuenta/getDatos/"+ miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

   public HttpResponse<String> hacerReserva(String nombreCentro, String nombreActividad, String tipo, HorarioDTO horarioDTO, Long codigoReservaPadre){
       String reservarJSON = "";
       try {
           ObjectMapper jsonObjectMapper = new ObjectMapper();
           Reserva2DTO reservarDTO = new Reserva2DTO(miniCuenta.getMailMiniCuenta(),nombreCentro,nombreActividad,tipo,horarioDTO,null,codigoReservaPadre,null);
           reservarJSON = jsonObjectMapper.writeValueAsString(reservarDTO);
       }catch (Exception e){
           throw new RuntimeException(e);
       }
       try {
           HttpResponse<String> response = Unirest.post("http://localhost:8080/usuario/reserva")
                   .header("Content-Type", "application/json")
                   .body(reservarJSON)
                   .asString();
           return response;
       }catch (Exception e){
           throw new RuntimeException(e);
       }
   }

}
