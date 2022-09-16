package com.tictok.RUCliente;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Controller_Vista2_JavaFX implements Initializable {


    @Autowired
    private UsuarioRest usuarioRest;

    @FXML
    public Button btnGuardar;
    public Button btnCancelar;
    public TextField txtNombre;
    public TextField txtDirec;
    public TextField txtTel;

    public Button btnOK;


    public Controller_Vista2_JavaFX() {
        System.out.println("COnstructor!!!");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void guardarDatos(ActionEvent actionEvent) throws UnirestException, IOException {
        String nombre = txtNombre.getText();
        String direc = txtDirec.getText();
        String tel = txtTel.getText();

        Integer responseCode = usuarioRest.guardarUsuario(nombre, direc, tel);
        if (responseCode==409){
            abrirVentanaEmergenteError();
        }

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

    public void abrirVentanaEmergenteError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(Controller_Vista2_JavaFX.class.getResourceAsStream("vent_emergente_error.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Error");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void volverAVista1(ActionEvent actionEvent) throws IOException {
        //en realidad no vuelvo a cargar la vista 1 pq sino se me duplican las ventanas, solo cierro la 2

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close(); //cierro la ventana en la que estoy

    }

    public void cerrarVentEmergError(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close(); //cierro la ventana en la que estoy
    }
}
