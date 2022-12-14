package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.ReservaDTO;
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

    public HttpResponse<String> buscarReservasFromUsuarioLogeado(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/usuario/getReservas/"+ miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

   public static HttpResponse<String> hacerReserva(String nombreCentro, String nombreActividad, String tipo, HorarioDTO horarioDTO, Long codigoReservaPadre, MiniCuenta miniCuenta){
       String reservarJSON = "";
       try {
           ObjectMapper jsonObjectMapper = new ObjectMapper();
           ReservaDTO reservarDTO = new ReservaDTO(miniCuenta.getMailMiniCuenta(),nombreCentro,nombreActividad,tipo,horarioDTO,null,codigoReservaPadre,null, null);
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

    public static HttpResponse<String> hacerReservaCanchaHija(Long codigoReservaPadre, MiniCuenta miniCuenta){
        String reservarJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            ReservaDTO reservarDTO = new ReservaDTO(miniCuenta.getMailMiniCuenta(), null ,null,"Cancha",null,null,codigoReservaPadre,null, null);
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

    public static HttpResponse<String> deleteReserva(String tipo, long numero){
        try {
            HttpResponse<String> response = Unirest.delete("http://localhost:8080/usuario/deleteReserva/" + tipo + "/" + numero)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> getReservaCanchaFromCodigo(String codigoReserva){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/usuario/getReservaCanchaFromCodigo/" + codigoReserva)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerActividades(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividades")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerActividadesPageable(int page, int size){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividades/"+ page + "/" + size)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchas(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/canchas")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchasPageable(int page, int size){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/canchas/"+ page + "/" + size)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    public HttpResponse<String> obtenerActividadesByFiltro(String filtro){
//        try {
//            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividades/"+filtro)
//                    .header("Content-Type", "application/json")
//                    .asString();
//            return response;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }

    public HttpResponse<String> obtenerActividadesByFiltroPageable(String filtro, int page, int size){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividades/" + filtro + "/" + page + "/" + size)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    public HttpResponse<String> obtenerCanchasByFiltro(String filtro){
//        try {
//            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/canchas/"+filtro)
//                    .header("Content-Type", "application/json")
//                    .asString();
//            return response;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }

    public HttpResponse<String> obtenerCanchasByFiltroPageable(String filtro, int page, int size){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/canchas/" + filtro + "/" + page + "/" + size)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> obtenerActividadConCupos(String nombreCentro, String nombreActividad){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividad/"+nombreCentro+"/"+nombreActividad)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> obtenerCanchaConCupos(String nombreCentro, String nombreCancha){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/cancha/"+nombreCentro+"/"+nombreCancha)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
