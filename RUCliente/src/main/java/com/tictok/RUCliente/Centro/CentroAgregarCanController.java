package com.tictok.RUCliente.Centro;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroAgregarCanController implements Initializable {
    public TextField txtPrecio;
    public TextField txtCupos;
    public TextField txtNombre;

    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void guardarDatos(ActionEvent actionEvent) {
    }

    public void agregarActividad(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }
}
