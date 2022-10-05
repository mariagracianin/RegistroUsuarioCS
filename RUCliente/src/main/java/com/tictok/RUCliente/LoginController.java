package com.tictok.RUCliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

@Component
public class LoginController {

    @Autowired
    LoginRest loginRest;

    public Button btnIngresar;
    public TextField correoElectronico;
    public TextField password;

    public void ingresar(ActionEvent actionEvent) throws IOException {
        HttpResponse<String> response = loginRest.autenticar(correoElectronico.getText(), password.getText());
        System.out.println("ke");
        System.out.println(response.getCode() + "---code");
        System.out.println(response.getBody() + "---body");
        if (response.getCode() == 200) {
            System.out.println("ok--------------------------");
            //CuentaDTO cuentaDTO = new CuentaDTO("admin","contra","admin");
            //CuentaDTO cuentaDTO = new CuentaDTO("empresa@mail","contra","empresa");
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                CuentaDTO cuentaDTO = objectMapper.readValue(response.getBody(), CuentaDTO.class);
                System.out.println("ok2----------------------------");
            } catch (Exception e) {
                throw new RuntimeException(e + "------------------");
            }

            /*if(cuentaDTO.getTipo().equals("empresa")){
                cargarVistaEmpresa();
            }
            if(cuentaDTO.getTipo().equals("user")){
                //cargarVistaUsuario();
            }
            if(cuentaDTO.getTipo().equals("admin")){
                cargarVistaAdmin();
            }*/
        }else {
            //se queda en el login
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

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("administrador.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Administrador");

        stage.show(); //no es ventana emergente
    }
}
