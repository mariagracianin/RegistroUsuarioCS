package com.tictok.RUCliente;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmpresaRegistroEmplController implements Initializable {

    @Autowired
    private UsuarioRest usuarioRest;
    @Autowired
    LoginController loginController;
    @Autowired
    EmpresaController empresaController;

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
    private void guardarDatos(ActionEvent actionEvent) throws UnirestException, IOException {
        try {
            String nombresTxt = nombres.getText();
            String apellidosTxt = apellidos.getText();
            int cedulaTxt = Integer.parseInt(cedula.getText());
            String direccionTxt = direccion.getText();
            String telTxt = tel.getText();
            Double saldoInicialNum = Double.parseDouble(saldoInicial.getText());
            Double saldoSobregiroNum = Double.parseDouble(saldoSobregiro.getText());
            String mailTxt = mail.getText();
            String passwordTxt = contraseña.getText();
            LocalDate vencimientoCarneDATE = fechaVenCarne.getValue();
            String vencimientoCarne = vencimientoCarneDATE.toString();

        HttpResponse<String> responseCode = usuarioRest.guardarUsuario(mailTxt, passwordTxt, cedulaTxt, vencimientoCarne, nombresTxt, apellidosTxt, telTxt, saldoInicialNum, saldoSobregiroNum);
        if (responseCode.getCode()==409){ //este es el error especifico de que el usuario ya existe, tenemos q ver
            //si puedo mostrar una variable en la pantalla que diga el mensaje? para no hacer n vistas distintas
            etVariableMensajeError.setText("mensaje error que viene del server");
            abrirVentanaEmergenteError();
        }else if (responseCode.getCode()==200){
            abrirVentanaEmergenteExito();
        }

        } catch (NumberFormatException e){
            abrirVentanaEmergenteError();
        }

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
    private void chequeoDatos(String nombresTxt, String apellidosTxt, String direccionTxt, String telTxt,
                             String mailTxt, String passwordTxt) throws IOException {
        if ( nombresTxt.isEmpty() || apellidosTxt.isEmpty() || direccionTxt.isEmpty() ||
                telTxt.isEmpty() || mailTxt.isEmpty() || passwordTxt.isEmpty() ||
                hasANumber(nombresTxt)|| hasANumber(apellidosTxt)|| (!isAnEmail(mailTxt)) ){
            //mail: chequea q tenga un arroba, un punto algo, alguna letra mas, en minusculas, permite numeros y puntos
            abrirVentanaEmergenteError();
        }
        try{
            //cedula,saldos ya estan chequeados arriba cuando los parseo
            //direccion,contraseña chequeo?
            Integer.parseInt(telTxt);


        }catch (NumberFormatException e){
            abrirVentanaEmergenteError();
        }

    }
    private static boolean hasANumber(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isAnEmail(String mail){
        Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mather = pat.matcher(mail);

        return mather.find();

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

        loginController.cargarVistaEmpresa();

    }

    private void abrirVentanaEmergenteError() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("vent_emergente_error.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Error");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        loginController.cargarVistaEmpresa();
    }

    private void salir(ActionEvent actionEvent) throws IOException {
        empresaController.salir(actionEvent);
    }

    private void cerrarVentEmergError(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close(); //cierro la ventana en la que estoy
    }
    private void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {
        empresaController.mostrarTablaEmpleados(actionEvent);
    }

    private void mostrarLiquidacion(ActionEvent actionEvent) {
    }

}
