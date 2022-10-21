package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class EmpMisDatosController implements Initializable {

    @Autowired
    EmpleadoController empleadoController;

    @Autowired
    UsuarioRest usuarioRest;

    public Label lblNombre;
    public Label lblApellido;
    public Label lblCedula;
    public Label lblMail;
    public Label lblDireccion;
    public Label lblTel;
    public Label lblSaldoActual;
    public Label lblSobregiro;
    public Label lblFechaVenCarne;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HttpResponse<String> response = usuarioRest.buscarDatosFromUsuarioLogeado();
        if(response.getCode()==200){
            ObjectMapper objectMapper = new ObjectMapper();
            MegaUsuarioDTO megaUsuarioDTO = null;
            try {
                megaUsuarioDTO = objectMapper.readValue(response.getBody(), MegaUsuarioDTO.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(megaUsuarioDTO.getNombre());
            lblNombre.setText(megaUsuarioDTO.getNombre());
            lblApellido.setText(megaUsuarioDTO.getApellido());
            lblCedula.setText(""+ megaUsuarioDTO.getCedula());
            lblMail.setText(megaUsuarioDTO.getCuentaMail());
            lblDireccion.setText(megaUsuarioDTO.getAddress());
            lblTel.setText(megaUsuarioDTO.getTelefono());
            lblSaldoActual.setText("" + megaUsuarioDTO.getSaldo());
            lblSobregiro.setText("" +megaUsuarioDTO.getSobregiro());
            lblFechaVenCarne.setText(megaUsuarioDTO.getVencimientoCarne());
        }else {
            throw new RuntimeException();
        }
    }


    public void verReservas(ActionEvent actionEvent) throws IOException {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empleadoController.verCanchas(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
