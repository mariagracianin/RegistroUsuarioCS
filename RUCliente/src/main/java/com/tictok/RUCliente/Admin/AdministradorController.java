package com.tictok.RUCliente.Admin;

import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdministradorController {
    public Button btnRegistrarEmpresa;
    public Button btnRegistrarCentro;
    public Button btnVerEmpresas;
    public Button btnVerCentros;
    public Button btnLiquidacion;
    public Button btnSalir;

    @FXML
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
        stageActual.show();

    }
    @FXML
    public void registrarEmpresa(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("adminRegistroEmpresa.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nueva empresa");

        stage.show(); //no es ventana emergente
    }

    public void mostrarTablaEmpresas(ActionEvent actionEvent) {
    }
    @FXML
    public void mostrarTablaCentros(ActionEvent actionEvent) {
    }

    @FXML
    public void registrarCentro(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("adminRegistroCentro.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nuevo centro");

        stage.show(); //no es ventana emergente
    }

    public void mostrarBalanceEmpresas(ActionEvent actionEvent) {
    }

    public void mostrarBalanceCentros(ActionEvent actionEvent) {
    }
}
