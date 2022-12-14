package com.tictok.RUCliente.Admin;
import com.tictok.RUCliente.AdminRest;
import com.tictok.RUCliente.Centro.CentroController;
import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class AdministradorController {
    public Button btnRegistrarEmpresa;
    public Button btnRegistrarCentro;
    public Button btnLiquidacion;
    public Button btnSalir;
    @Autowired
    AdminRest adminRest;
    @FXML
    public void salir(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
        stageActual.setTitle("Login");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
        stageActual.setScene(escena);
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stageActual.getIcons().add(new Image(AdministradorController.class.getResourceAsStream("logo.png")));
        stageActual.show();
        stageActual.centerOnScreen();
    }
    @FXML
    public void registrarEmpresa(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("adminRegistroEmpresa.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nueva empresa");
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stage.getIcons().add(new Image(AdministradorController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }
    @FXML
    public void registrarCentro(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("adminRegistroCentro.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nuevo centro");
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stage.getIcons().add(new Image(AdministradorController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }
    public void mostrarBalanceEmpresas(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("administradorBalanceEmpresas.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Balance empresas");
        escena.getWindow().setWidth(1350);
        escena.getWindow().setHeight(700);
        stage.getIcons().add(new Image(AdministradorController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }
    public void mostrarBalanceCentros(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(AdministradorController.class.getResourceAsStream("administradorBalanceCentros.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Balance centros");
        escena.getWindow().setWidth(1350);
        escena.getWindow().setHeight(700);
        stage.getIcons().add(new Image(AdministradorController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
    }
    public void cargaDeDatos(ActionEvent actionEvent) {
        adminRest.cargarDatos();
    }
}