package com.tictok.RUCliente.Empleado;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmpCanchasController implements Initializable {
    @Autowired
    EmpleadoController empleadoController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void verReservas(ActionEvent actionEvent) {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) {
        empleadoController.verDatos(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
