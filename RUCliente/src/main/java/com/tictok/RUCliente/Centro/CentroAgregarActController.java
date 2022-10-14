package com.tictok.RUCliente.Centro;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroAgregarActController implements Initializable {
    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void verActividades(ActionEvent actionEvent) {
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) {
    }
}
