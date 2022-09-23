package com.tictok.RUCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Login_Controller {
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

        Parent root = fxmlLoader.load(Empresa_RegistroEmpl_Controller.class.getResourceAsStream("empresa.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Empresa");

        stage.show(); //no es ventana emergente
    }
}
