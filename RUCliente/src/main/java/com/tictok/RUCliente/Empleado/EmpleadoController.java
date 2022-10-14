package com.tictok.RUCliente.Empleado;

import com.tictok.RUCliente.Empresa.EmpresaController;
import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmpleadoController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void verReservas(ActionEvent actionEvent) {
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpleadoController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empActividades.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Actividades");

        stage.show(); //no es ventana emergente
    }

    public void verCanchas(ActionEvent actionEvent) {
    }

    public void verDatos(ActionEvent actionEvent) {
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
        stageActual.setTitle("Login");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
        stageActual.setScene(escena);
        stageActual.show();
    }
}
