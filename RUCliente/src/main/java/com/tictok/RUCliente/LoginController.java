package com.tictok.RUCliente;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    @Autowired
    LoginRest loginRest;
    MiniCuenta miniCuenta;

    public Button btnIngresar;
    public TextField correoElectronico;
    public PasswordField password;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        HttpResponse<String> response = loginRest.autenticar(correoElectronico.getText(), password.getText());
        if (response.getCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            MiniCuentaDTO miniCuentaDTO = objectMapper.readValue(response.getBody(), MiniCuentaDTO.class);

            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("empresa")){
                Node source = (Node)  actionEvent.getSource();
                Stage stageActual  = (Stage) source.getScene().getWindow();
                stageActual.close();
                cargarVistaEmpresa();
            }
            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("user")){
                //cargarVistaUsuario();
            }
            if(miniCuentaDTO.getTipoMiniCuentaDTO().equals("admin")){
                Node source = (Node)  actionEvent.getSource();
                Stage stageActual  = (Stage) source.getScene().getWindow();
                stageActual.close();
                cargarVistaAdmin();
            }
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
            Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Login");
            Scene escena = new Scene(root);
            escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
            stage.setScene(escena);
            stage.show();
        }
    }

    public void cargarVistaEmpresa() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("empresa.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Empresa");

        stage.show(); //no es ventana emergente
    }
    public void cargarVistaAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/com/tictok/RUCliente/Admin/administrador.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Administrador");

        stage.show(); //no es ventana emergente
    }
}
