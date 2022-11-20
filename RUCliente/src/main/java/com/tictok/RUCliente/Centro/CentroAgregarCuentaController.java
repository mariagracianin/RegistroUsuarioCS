package com.tictok.RUCliente.Centro;

import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroAgregarCuentaController implements Initializable {
    public TextField contraseñaCuenta;
    public TextField mailCuenta;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void irACheckIn(ActionEvent actionEvent) throws IOException {
        centroController.irACheckIn(actionEvent);

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

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void guardarDatos(ActionEvent actionEvent) {
        centroDeportivoRest.guardarNuevaCuentaDeCentro(mailCuenta.getText(), contraseñaCuenta.getText());
        mailCuenta.setText("");
        contraseñaCuenta.setText("");
    }
}
