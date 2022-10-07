package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.NuevaEmpresaDTO;
import org.springframework.stereotype.Component;

@Component
public class EmpresaRest {

    public HttpResponse<String> guardarEmpresa(String mail, String password, String nombreEmpresa, String adress, String telefono, String encargado) {
        String nuevaEmpresaJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            NuevaEmpresaDTO nuevaEmpresaDTO = new NuevaEmpresaDTO(mail, password, nombreEmpresa, adress, telefono, encargado);
            nuevaEmpresaJSON = jsonObjectMapper.writeValueAsString(nuevaEmpresaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/empresa")
                    .header("Content-Type", "application/json")
                    .body(nuevaEmpresaJSON)
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
