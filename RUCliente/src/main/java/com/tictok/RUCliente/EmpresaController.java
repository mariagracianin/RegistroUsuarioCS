package com.tictok.RUCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component //importante!!!!!
public class EmpresaController {
    public Button btnSalir;
    public Button btnRegistrarNC;
    public Button btnVerTablaEmpleados;

    public void salir(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();

    }

    public void registrarUsuario(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("empresa_registro_empleado.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nuevo empleado");

        stage.show(); //no es ventana emergente

    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }

    public void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaTablaEmplController.class.getResourceAsStream("empresa_tablaEmpleados.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");

        stage.show(); //no es ventana emergente
    }
}
