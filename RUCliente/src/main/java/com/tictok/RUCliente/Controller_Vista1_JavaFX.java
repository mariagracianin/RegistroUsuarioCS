package com.tictok.RUCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component //importante!!!!!
public class Controller_Vista1_JavaFX {
    public Button btnSalir;
    public Button btnRegistrarNC;

    public void salir(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();

    }

    public void pasarAVista2(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(Controller_Vista2_JavaFX.class.getResourceAsStream("vista2_archivo.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registrar nuevo empleado");

        stage.show(); //no es ventana emergente

    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }
}
