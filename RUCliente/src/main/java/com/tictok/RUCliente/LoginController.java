package com.tictok.RUCliente;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;

@Component
public class LoginController {

    @Autowired
    LoginRest loginRest;
    @Autowired
    MiniCuenta miniCuenta;

    public Button btnIngresar;
    public TextField correoElectronico;
    public PasswordField password;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        HttpResponse<String> response = loginRest.autenticar(correoElectronico.getText(), password.getText());
        if (response.getCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            MiniCuentaDTO miniCuentaDTO = objectMapper.readValue(response.getBody(), MiniCuentaDTO.class);
            miniCuenta.setMailMiniCuenta(miniCuentaDTO.getMailMiniCuentaDTO());
            miniCuentaDTO.setTipoMiniCuentaDTO(miniCuentaDTO.getTipoMiniCuentaDTO());

            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("empresa")){
                cargarVistaEmpresa(actionEvent);
            }
            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("user")){
                cargarVistaUsuario(actionEvent);
            }
            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("admin")){
                cargarVistaAdmin(actionEvent);
            }
            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("centro")){
                cargarVistaCentro(actionEvent);
            }
        }else {
            //abro ventana emergente error
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("vent_emergente_error.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }
    }
    @FXML
    private void salirVentanasEmergentes(ActionEvent actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    private void cargarVistaUsuario(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empMisReservas.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("GetFit");

        stage.show(); //no es ventana emergente
    }
    public void cargarVistaAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/com/tictok/RUCliente/Admin/administrador.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Administrador");

        stage.show(); //no es ventana emergente
    }

    public void cargarVistaEmpresa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("empresa.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Empresa");

        stage.show(); //no es ventana emergente
    }
    public void cargarVistaCentro(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centro.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Centro");

        stage.show(); //no es ventana emergente
    }

}
