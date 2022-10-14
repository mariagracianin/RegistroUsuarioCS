package com.tictok.RUCliente.Admin;

import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AdministradorRegCentroController implements Initializable {
    public TextField nombre;
    public TextField encargado;
    public TextField direccion;
    public TextField tel;
    public TextField mailCuenta1;
    public TextField contraseñaCuenta1;
    public TextField rut;
    public TextField razonSocial;
    public ChoiceBox opcionesBarrios;

    private ObservableList<String> opcionesBarriosList = FXCollections.observableArrayList("Pocitos","Centro","Ciudad Vieja","Tres Cruces");

    @Autowired
    AdministradorController administradorController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

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
        //opcionesBarrios.getValue();
        centroDeportivoRest.guardarCentroDeportivo(mailCuenta1.getText(),contraseñaCuenta1.getText(),nombre.getText(),direccion.getText(),tel.getText(),encargado.getText(), rut.getText(),razonSocial.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opcionesBarrios.setItems(opcionesBarriosList);
    }
}
