package com.tictok.RUCliente.Centro;

import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(CentroController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroAgregarAct.fxml"));
        stageActual.setTitle("Agregar actividad");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stageActual.setScene(escena);
        stageActual.show();
    }

    public void verActividades(ActionEvent actionEvent) {
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(CentroController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroBalance.fxml"));
        stageActual.setTitle("Balance");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stageActual.setScene(escena);
        stageActual.show();
    }

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

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(CentroController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroAgregarCancha.fxml"));
        stageActual.setTitle("Agregar cancha");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stageActual.setScene(escena);
        stageActual.show();
    }

    public void verCanchas(ActionEvent actionEvent) {
    }

    public void irACheckIn(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(CentroController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroCheckIn.fxml"));
        stageActual.setTitle("Ingresar Check-In");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stageActual.setScene(escena);
        stageActual.show();
    }
}
