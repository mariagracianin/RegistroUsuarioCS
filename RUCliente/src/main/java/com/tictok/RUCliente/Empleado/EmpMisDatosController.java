package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class EmpMisDatosController implements Initializable {

    @Autowired
    UsuarioRest usuarioRest;
    @Autowired
    EmpMisReservasController empMisReservasController;

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
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpMisDatosController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empMisReservas.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Mis Reservas");

        stage.show(); //no es ventana emergente
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verCanchas(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empMisReservasController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empMisReservasController.cerrarSesion(actionEvent);
    }
}
