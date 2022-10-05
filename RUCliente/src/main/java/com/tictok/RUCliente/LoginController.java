package com.tictok.RUCliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tictok.Commons.CuentaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    @Autowired
    LoginRest loginRest;

    ObjectMapper jsonObjectMapper = new ObjectMapper();

    public Button btnIngresar;
    public TextField correoElectronico;
    public TextField password;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        cargarVistaEmpresa();

        /*System.out.println(password.getText()+"---------------------------------------------------------------");
        if(!validarDatos(correoElectronico.getText(), password.getText())){
            //
        }else{
            String responseBody = loginRest.autenticar(correoElectronico.getText(),password.getText()).getBody();
            CuentaDTO cuenta = jsonObjectMapper.readValue(responseBody, CuentaDTO.class);

            if(cuenta.getTipo().equals("empresa")){
                cargarVistaEmpresa();
            }
            if(cuenta.getTipo().equals("usuario")){
                //cargarVistaUsuario();
            }
            if(cuenta.getTipo().equals("admin")){
                //cargarVistaAdmin();
            }
        }*/
    }
    public boolean validarDatos(String correo, String contraseña){
        //hacer chequeos, si alguno facha return false
        return true;
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

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("administrador.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Administrador");

        stage.show(); //no es ventana emergente
    }
}
