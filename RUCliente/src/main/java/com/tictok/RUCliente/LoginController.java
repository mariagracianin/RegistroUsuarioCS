package com.tictok.RUCliente;

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

    public Button btnIngresar;
    public TextField correoElectronico;
    public TextField contraseña;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        boolean validar = validarDatos(correoElectronico.getText(),contraseña.getText());
        //buscar por mail en cada tabla, obtener objeto tipo de usuario
        //if (tipoUsuario == Empresa){
        cargarVistaEmpresa();
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
}
