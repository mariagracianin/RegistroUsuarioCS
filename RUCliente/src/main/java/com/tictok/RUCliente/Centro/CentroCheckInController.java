package com.tictok.RUCliente.Centro;

import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
        lblDebeSeleccionar.setDisable(true);
    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void irAUsuario(ActionEvent actionEvent) throws IOException {

        if (btnConReserva.isSelected()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            CentroCheckInUsuarioReservasController controller = fxmlLoader.getController();
            controller.setCedulaUsuario(Integer.parseInt(txtCedulaUsuario.getText()));

            Parent root = fxmlLoader.load(CentroCheckInUsuarioReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/centroCheckInUsuarioReservas.fxml"));


            Stage stage = new Stage();
            Scene escena = new Scene(root);
            stage.setScene(escena);
            escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
            stage.show();
        } else if (btnSinReserva.isSelected()) {
            System.out.println("sin reserva");
        } else {
            lblDebeSeleccionar.setDisable(false);
        }
    }
}
