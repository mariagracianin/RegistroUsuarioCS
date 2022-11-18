package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.CuentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminRest {
    @Autowired
    private MiniCuenta miniCuenta;

    public HttpResponse<String> obtenerBalanceEmpresas(int mes, int year){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/admin/balance/empresas/" + mes + "/" + year)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> obtenerBalanceCentroDeportivo(int mes, int year){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/admin/balance/centrosDeportivos/" + mes + "/" + year)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> cargarDatos(){
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/admin/cargaDeDatos")
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
