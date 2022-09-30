package com.tictok.RUCliente;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class EmpresaRegistroEmplController implements Initializable {

    @Autowired
    private UsuarioRest usuarioRest;

    @FXML
    public Button btnGuardar;
    public Button btnCancelar;
    public Button btnOK;

    public TextField nombres;
    public TextField direccion;
    public TextField tel;
    public TextField apellidos;
    public TextField cedula;
    public TextField saldoInicial;
    public DatePicker fechaVenCarne;
    public TextField mail;
    public TextField contraseña;
    public TextField saldoSobregiro;

    public Label etVariableMensajeError;



    public EmpresaRegistroEmplController() {
        System.out.println("COnstructor!!!");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public void guardarDatos(ActionEvent actionEvent) throws UnirestException, IOException {
        String nombresTxt = nombres.getText();
        String apellidosTxt = apellidos.getText();
        int cedulaTxt = Integer.parseInt(cedula.getText());
        String direccionTxt = direccion.getText();
        String telTxt = tel.getText();
        Double saldoInicialNum = Double.parseDouble(saldoInicial.getText());
        Double saldoSobregiroNum = Double.parseDouble(saldoSobregiro.getText());
        String mailTxt = mail.getText();
        String passwordTxt = contraseña.getText();
        LocalDate vencimientoCarne = fechaVenCarne.getValue();

        Integer responseCode = usuarioRest.guardarUsuario(mailTxt, passwordTxt, cedulaTxt, vencimientoCarne, nombresTxt, apellidosTxt, telTxt, saldoInicialNum, saldoSobregiroNum);
        if (responseCode==409){ //este es el error especifico de que el usuario ya existe, tenemos q ver
            //si puedo mostrar una variable en la pantalla que diga el mensaje? para no hacer n vistas distintas
            System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkk");
            etVariableMensajeError.setText("mensaje error que viene del server");
            abrirVentanaEmergenteError();
        }else if (responseCode==200){
            abrirVentanaEmergenteExito();
        }

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }

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

    public void abrirVentanaEmergenteError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("vent_emergente_error.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Error");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void volverAEmpresa(ActionEvent actionEvent) throws IOException {
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
