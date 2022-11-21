package com.tictok.RUCliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CentroDeportivoRest {

    @Autowired
    private MiniCuenta miniCuenta;

    public HttpResponse<String> guardarCentroDeportivo(String mail, String password, String nombreCentro, String adress, String telefono, String encargado, String rut, String razonsocial, String barrio) {
        String nuevoCentroJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            NuevoCentroDTO nuevoCentroDTO = new NuevoCentroDTO(mail, password, nombreCentro, adress, telefono, encargado, rut, razonsocial, barrio);
            nuevoCentroJSON = jsonObjectMapper.writeValueAsString(nuevoCentroDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro")
                    .header("Content-Type", "application/json")
                    .body(nuevoCentroJSON)
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> guardarNuevaCuentaDeCentro(String mail, String password) {
        String nuevaCuentaJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            CuentaDTO cuentaDTO = new CuentaDTO(mail,password,"centro");
            nuevaCuentaJSON = jsonObjectMapper.writeValueAsString(cuentaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro/" + miniCuenta.getMailMiniCuenta() + "/postCuenta")
                    .header("Content-Type", "application/json")
                    .body(nuevaCuentaJSON)
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> obtenerCentroLogeado(MiniCuenta miniCuenta){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/obtenerCentro/"+ miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> guardarActividad(String nombreServicio, Double precio, Integer cupos, Boolean paseLibre, String imagen, List<HorarioDTO> horarios){
        String nuevaActividadJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            NuevoServicioDTO nuevaActividadDTO = new NuevoServicioDTO(nombreServicio,precio,cupos,paseLibre,imagen,horarios);
            nuevaActividadJSON = jsonObjectMapper.writeValueAsString(nuevaActividadDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro/postActividad/" + miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .body(nuevaActividadJSON)
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> guardarCancha(String nombreServicio, Double precio, Integer cupos, String imagen, List<HorarioDTO> horarios){
        String nuevaCanchaJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            NuevoServicioDTO nuevaCanchaDTO = new NuevoServicioDTO(nombreServicio,precio,cupos,false,imagen,horarios);
            nuevaCanchaJSON = jsonObjectMapper.writeValueAsString(nuevaCanchaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro/postCancha/" + miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .body(nuevaCanchaJSON)
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> obtenerActividadesFromCentroLogeado(MiniCuenta miniCuenta){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/getActividades/"+miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    public static HttpResponse<String> obtenerCanchasFromCentroLogeado(MiniCuenta miniCuenta){
//        try {
//            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/getCanchas/"+miniCuenta.getMailMiniCuenta())
//                    .header("Content-Type", "application/json")
//                    .asString();
//            return response;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }

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

    public HttpResponse<String> buscarReservasFromUsuario(int cedula){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/usuario/getReservasByCedula/"+cedula+"/"+ miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> hacerCheckInSinReserva(int cedula, String nombreActividad, String tipo, HorarioDTO horarioDTO, Long codigoCheckIn, MiniCuenta miniCuenta) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse<String> response2 = obtenerCentroLogeado(miniCuenta);
        CentroDeportivoDTO centroDeportivoDTO = objectMapper.readValue(response2.getBody(), CentroDeportivoDTO.class);

        String checkInJSON = "";

        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            CheckInDTO checkInDTO = new CheckInDTO(cedula, centroDeportivoDTO.getNombreCentro(),nombreActividad,tipo,horarioDTO,null,null,null);
            checkInJSON = jsonObjectMapper.writeValueAsString(checkInDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro/checkIn")
                    .header("Content-Type", "application/json")
                    .body(checkInJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> hacerCheckInConReserva(String tipo, Long codigoReserva) throws JsonProcessingException {
        String checkInJSON = "";

        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            CheckInDTO checkInDTO = new CheckInDTO(tipo, codigoReserva);
            checkInJSON = jsonObjectMapper.writeValueAsString(checkInDTO);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/centro/checkIn/reserva")
                    .header("Content-Type", "application/json")
                    .body(checkInJSON)
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerBalanceCentro(int mes, int year){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/balance/" + miniCuenta.getMailMiniCuenta() + "/"+ mes + "/" + year)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
