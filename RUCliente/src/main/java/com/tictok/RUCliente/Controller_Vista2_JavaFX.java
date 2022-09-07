package com.tictok.RUCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Controller_Vista2_JavaFX implements Initializable {

    @FXML
    public Button btnGuardar;
    public Button btnCancelar;
    public TextField txtNombre;
    public TextField txtDirec;
    public TextField txtTel;


    public Controller_Vista2_JavaFX() {
        System.out.println("COnstructor!!!");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void guardarDatos(ActionEvent actionEvent) {

        System.out.println(txtNombre.getText());

        //Acá hay que hacer la request

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();


    }


    public void volverAVista1(ActionEvent actionEvent) throws IOException {
        //en realidad no vuelvo a cargar la vista 1 pq sino se me duplican las ventanas, solo cierro la 2

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close(); //cierro la ventana en la que estoy

    }
}
