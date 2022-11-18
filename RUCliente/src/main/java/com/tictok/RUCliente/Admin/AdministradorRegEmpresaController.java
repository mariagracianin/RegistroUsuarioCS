package com.tictok.RUCliente.Admin;

import com.mashape.unirest.http.HttpResponse;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import com.tictok.RUCliente.EmpresaRest;
import com.tictok.RUCliente.LoginController;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    public TextField rut;
    public TextField razonSocial;

    @Autowired
    AdministradorController administradorController;
    @Autowired
    LoginController loginController;

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

    public void guardarDatos(ActionEvent actionEvent) throws IOException {
        HttpResponse<String> responseCode = empresaRest.guardarEmpresa(mailCuenta1.getText(),contraseñaCuenta1.getText(),nombre.getText(),direccion.getText(),tel.getText(),encargado.getText(), rut.getText(), razonSocial.getText());

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

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("ventSaldoInsuficienteAct.fxml"));
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

    public void mostrarBalanceEmpresas(ActionEvent actionEvent) throws IOException {
        administradorController.mostrarBalanceEmpresas(actionEvent);
    }

    public void mostrarBalanceCentros(ActionEvent actionEvent) throws IOException{
        administradorController.mostrarBalanceCentros(actionEvent);
    }
}
