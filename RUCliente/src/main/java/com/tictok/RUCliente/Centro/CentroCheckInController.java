package com.tictok.RUCliente.Centro;

import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroCheckInController implements Initializable {

    public TextField txtCedulaUsuario;
    public RadioButton btnConReserva;
    public RadioButton btnSinReserva;
    final ToggleGroup group = new ToggleGroup();
    public Label lblDebeSeleccionar;

    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConReserva.setToggleGroup(group);
        btnSinReserva.setToggleGroup(group);
    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        centroController.verCanchas(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void irAUsuario(ActionEvent actionEvent) throws IOException {

        if (btnConReserva.isSelected()) {
            Node source = (Node)  actionEvent.getSource();
            Stage stageActual  = (Stage) source.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(CentroCheckInUsuarioReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroCheckInUsuarioReservas.fxml"));
            CentroCheckInUsuarioReservasController controller = fxmlLoader.getController();
            controller.setCedulaUsuario(Integer.parseInt(txtCedulaUsuario.getText()));

            Scene escena = new Scene(root);
            stageActual.setScene(escena);
            escena.getWindow().setWidth(1250);
            escena.getWindow().setHeight(600);
            escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
            stageActual.show();
            stageActual.centerOnScreen();
        } else if (btnSinReserva.isSelected()) {
            Node source = (Node)  actionEvent.getSource();
            Stage stageActual  = (Stage) source.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            Parent root = fxmlLoader.load(CentroCheckInUsuarioReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroCheckInUsuarioSinReserva.fxml"));

            CentroCheckInUsuarioSinReservaController controller = fxmlLoader.getController();
            controller.setCedulaUsuario(Integer.parseInt(txtCedulaUsuario.getText()));
            System.out.println("CEDULA EN TXT1: " + txtCedulaUsuario.getText() + "-----------------------------");
            controller.inicializar();

            Scene escena = new Scene(root);
            stageActual.setScene(escena);
            escena.getWindow().setWidth(1250);
            escena.getWindow().setHeight(700);
            escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
            stageActual.show();
            stageActual.centerOnScreen();
        } else {
            lblDebeSeleccionar.setText("Debe seleccionar alguna de las opciones");
        }
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        centroController.agregarCuenta(actionEvent);
    }
}
