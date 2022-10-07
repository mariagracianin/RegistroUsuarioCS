package com.tictok.RUCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdministradorRegEmpresaController {
    @Autowired
    EmpresaRest empresaRest;

    public TextField nombre;
    public TextField encargado;
    public TextField direccion;
    public TextField tel;
    public TextField mailCuenta1;
    public TextField contraseñaCuenta1;
    @Autowired
    AdministradorController administradorController;
    @FXML
    public void registrarCentro(ActionEvent actionEvent) throws IOException {
        administradorController.registrarCentro(actionEvent);
    }
    @FXML
    public void mostrarTablaEmpresas(ActionEvent actionEvent) {
    }
    @FXML
    public void mostrarTablaCentros(ActionEvent actionEvent) {
    }
    @FXML
    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }
    @FXML
    public void salir(ActionEvent actionEvent) throws IOException {
        administradorController.salir(actionEvent);
    }

    public void guardarDatos(ActionEvent actionEvent) {
        empresaRest.guardarEmpresa("mgnin@correo","gatito","gatitoSA","esqu","2817392","gatoEncargado");
    }
}
