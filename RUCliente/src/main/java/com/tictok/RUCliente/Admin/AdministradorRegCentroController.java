package com.tictok.RUCliente.Admin;

import com.tictok.RUCliente.CentroDeportivoRest;
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
    @FXML
    public ChoiceBox opcionesBarrios;
    ObservableList<String> opcionesBarriosList = FXCollections.observableArrayList("Ciudad Vieja","Centro","Barrio Sur","Cordón","Palermo","Parque Rodó", "Punta Carretas", "Pocitos","Buceo", "La Unión", "La Blanqueada", "Parque Batlle", "Villa Dolores", "Malvín", "Malvín Norte", "Punta Gorda","Carrasco","Carrasco Norte", "Tres Cruces", "La Comercial", "Villa Muñoz", "Goes", "Aguada", "Reducto", "Arroyo Seco", "Bella Vista", "La Figurita", "Jacinto Vera", "Larrañaga", "Maroñas","Flor de Maroñas","Villa Española", "Mercado Modelo", "Brazo Oriental", "Atahualpa", "Prado","Capurro","Paso Molino", "Belvedere","Sayago","Aires Puros","Cerrito de la Victoria", "Ituzaingó", "Bella Italia","Punta de Rieles", "Jardines del Hipódromo", "Piedras Blancas", "Casavalle","Manga", "Peñarol", "Nuevo París", "La Teja","Cerro Norte", "Villa del Cerro");

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
        //opcionesBarrios.getValue(); <- con esto obtenemos el valor de la choicebox
        centroDeportivoRest.guardarCentroDeportivo(mailCuenta1.getText(),contraseñaCuenta1.getText(),nombre.getText(),direccion.getText(),tel.getText(),encargado.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opcionesBarrios.setItems(opcionesBarriosList);

    }
}
