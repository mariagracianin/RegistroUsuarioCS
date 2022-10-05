package com.tictok.RUCliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    @Autowired
    private LoginRest loginRest;

    ObjectMapper mapper = new ObjectMapper();

    public Button btnIngresar;
    public TextField correoElectronico;
    public TextField password;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        /*if(!validarDatos(correoElectronico.getText(), password.getText())){
            //no hace nada
        }else {
            String responseBody = loginRest.autenticar(correoElectronico.getText(),password.getText()).getBody();
            CuentaDTO cuenta = mapper.readValue(responseBody, CuentaDTO.class);

            if(cuenta.getTipo()=="empresa"){
                cargarVistaEmpresa();
            }else {
                throw new RuntimeException("NO EXISTE EMPRESA CON ESE MAIL Y CONTRASENA");
            }
        }*/
        cargarVistaEmpresa();

        //buscar por mail en cada tabla, obtener objeto tipo de usuario
        //if (tipoUsuario == Empresa){

    }

    public boolean validarDatos(String correo, String password) throws JsonProcessingException {
        //hacer chequeos, si alguno facha return false
        return true;
    }
    public void cargarVistaEmpresa() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("empresa.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Empresa");

        stage.show(); //no es ventana emergente
    }
}
