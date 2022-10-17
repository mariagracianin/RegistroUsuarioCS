package com.tictok.RUCliente.Admin;

import com.mashape.unirest.http.HttpResponse;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import com.tictok.RUCliente.LoginController;
import com.tictok.RUCliente.Main;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    LoginController loginController;

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

    public void guardarDatos(ActionEvent actionEvent) throws IOException {
        HttpResponse<String> responseCode = centroDeportivoRest.guardarCentroDeportivo(mailCuenta1.getText(),contraseñaCuenta1.getText(),nombre.getText(),direccion.getText(),tel.getText(),encargado.getText(), rut.getText(),razonSocial.getText(),opcionesBarrios.getValue().toString());
        if(responseCode.getCode()==409){
            abrirVentanaEmergenteError();
        }else if(responseCode.getCode()==200){
            loginController.cargarVistaAdmin(actionEvent);
            abrirVentanaEmergenteExito();
        }
    }
    @FXML
    private void abrirVentanaEmergenteExito() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("vent_emergente_exito.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Éxito");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
    @FXML
    private void abrirVentanaEmergenteError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("vent_emergente_error.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Error");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void salirVentanasEmergentes(ActionEvent actionEvent){
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        opcionesBarrios.setItems(opcionesBarriosList);
    }
}
