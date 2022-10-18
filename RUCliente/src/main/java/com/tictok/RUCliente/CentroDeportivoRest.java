package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import org.springframework.stereotype.Component;

@Component
public class CentroDeportivoRest {

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

    public HttpResponse<String> obtenerActividades(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/actividades")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerActividadConCupos(String nombreCentro, String nombreActividad, String dia, String horaInicio, String horaFin){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/actividad/"+nombreCentro+"/"+nombreActividad+"/"+dia+"/"+horaInicio+"/"+horaFin)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerCanchas(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/centro/canchas")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
