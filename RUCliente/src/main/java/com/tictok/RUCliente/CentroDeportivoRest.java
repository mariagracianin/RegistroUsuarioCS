package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.Commons.NuevoServicioDTO;
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

    public HttpResponse<String> guardarActividad(String nombreServicio, Integer precio, Integer cupos, Boolean paseLibre, String imagen, List<HorarioDTO> horarios){
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

    public HttpResponse<String> guardarCancha(String nombreServicio, Integer precio, Integer cupos, String imagen, List<HorarioDTO> horarios){
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

    public HttpResponse<String> obtenerActividadesFromCentroLogeado(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/getActividades/"+miniCuenta.getMailMiniCuenta())
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchasFromCentroLogeado(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/getCanchas/"+miniCuenta.getMailMiniCuenta())
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

    public HttpResponse<String> obtenerActividadesByFiltro(String filtro){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividades/"+filtro)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchasByFiltro(String filtro){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/canchas/"+filtro)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerActividadConCupos(String nombreCentro, String nombreActividad){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/servicio/actividad/"+nombreActividad+"/"+nombreCentro)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchaConCupos(String nombreCentro, String nombreCancha){
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
