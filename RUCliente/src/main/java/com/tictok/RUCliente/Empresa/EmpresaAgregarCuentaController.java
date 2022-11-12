package com.tictok.RUCliente.Empresa;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmpresaAgregarCuentaController implements Initializable {

    @Autowired
    EmpresaController empresaController;

    public TextField mailCuenta;
    public TextField contraseñaCuenta;

    public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        empresaController.registrarUsuario(actionEvent);
    }

    public void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {
        empresaController.registrarUsuario(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        empresaController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        empresaController.salir(actionEvent);
    }

    public void guardarDatos(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
