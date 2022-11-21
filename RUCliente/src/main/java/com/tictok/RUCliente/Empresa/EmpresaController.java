package com.tictok.RUCliente.Empresa;

import com.tictok.RUCliente.Centro.CentroController;
import com.tictok.RUCliente.Empleado.EmpMisReservasController;
import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.LoginController;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component //importante!!!!!
public class EmpresaController implements Initializable {

    @Autowired
    LoginController loginController;

    public Button btnSalir;
    public Button btnRegistrarNC;
    public Button btnVerTablaEmpleados;

    public void salir(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
        stageActual.setTitle("Login");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
        stageActual.setScene(escena);
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stageActual.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stageActual.show();
        stageActual.centerOnScreen();

    }
    public void volverAVistaEmpresa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaController.class.getResourceAsStream("empresa.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Empresa");
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stage.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }
    public void registrarUsuario(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaController.class.getResourceAsStream("empresa_registro_empleado.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nuevo empleado");
        stage.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();

    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(CentroController.class.getResourceAsStream("/com/tictok/RUCliente/Empresa/empresaBalance.fxml"));
        stageActual.setTitle("Balance");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stageActual.setScene(escena);
        stageActual.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stageActual.show();
        stageActual.centerOnScreen();
        stageActual.setMaximized(true);
    }

    public void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaController.class.getResourceAsStream("empresa_tablaEmpleados.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getWindow().setWidth(1200);
        escena.getWindow().setHeight(600);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaController.class.getResourceAsStream("empresaAgregarCuenta.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.getIcons().add(new Image(EmpresaController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stage.centerOnScreen();
    }
}
