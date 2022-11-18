package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.tictok.Commons.CuentaDTO;
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

    public HttpResponse<String> guardarNuevaCuentaDeEmpresa(String mail, String password) {
        String nuevaCuentaJSON = "";
        try {
            ObjectMapper jsonObjectMapper = new ObjectMapper();
            CuentaDTO cuentaDTO = new CuentaDTO(mail,password,"empresa");
            nuevaCuentaJSON = jsonObjectMapper.writeValueAsString(cuentaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/empresa/" + miniCuenta.getMailMiniCuenta() + "/postCuenta")
                    .header("Content-Type", "application/json")
                    .body(nuevaCuentaJSON)
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

    public HttpResponse<String> obtenerBalanceGeneral(int mes, int year){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/empresa/" + miniCuenta.getMailMiniCuenta() + "/balance/"+ mes + "/" + year)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> obtenerBalanceDeUsuario(int cedulaUsario, int mes, int year, MiniCuenta miniCuenta){
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8080/empresa/" + miniCuenta.getMailMiniCuenta() + "/balanceUsuario/"+ cedulaUsario + "/" + mes + "/" + year)
                    .header("Content-Type", "application/json")
                    .asString();
            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
