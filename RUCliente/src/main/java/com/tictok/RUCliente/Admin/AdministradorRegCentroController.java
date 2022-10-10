package com.tictok.RUCliente.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdministradorRegCentroController {
    public TextField nombre;
    public TextField encargado;
    public TextField direccion;
    public TextField tel;
    public TextField mailCuenta1;
    public TextField contraseñaCuenta1;
    public TextField rut;
    public TextField razonSocial;
    @Autowired
    AdministradorController administradorController;
    @FXML
    public void registrarEmpresa(ActionEvent actionEvent) throws IOException {
        administradorController.registrarEmpresa(actionEvent);
    }
    @FXML
    private void salir(ActionEvent actionEvent) throws IOException {
        administradorController.salir(actionEvent);
    }

    public void mostrarTablaEmpresas(ActionEvent actionEvent) {
    }

    public void mostrarTablaCentros(ActionEvent actionEvent) {
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }

    public void guardarDatos(ActionEvent actionEvent) {
    }
}
