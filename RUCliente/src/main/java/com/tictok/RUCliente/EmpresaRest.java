package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.NuevaEmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaRest {

    @Autowired
    private MiniCuenta miniCuenta;

    public HttpResponse<String> guardarEmpresa(String mail, String password, String nombreEmpresa, String adress, String telefono, String encargado, String rut, String razonsocial) {
        String nuevaEmpresaJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            NuevaEmpresaDTO nuevaEmpresaDTO = new NuevaEmpresaDTO(mail, password, nombreEmpresa, adress, telefono, encargado, rut, razonsocial);
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

    public HttpResponse<String> obtenerUsuariosFromEmpresaLogeada(){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/empresa/"+ miniCuenta.getMailMiniCuenta() +"/usuarios")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
