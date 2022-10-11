package com.tictok.RUCliente.Empresa;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.LoginController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.UsuarioRest;
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
    @FXML
    public Button btnOK;
    @FXML
    public TextField nombres;
    @FXML
    public TextField direccion;
    @FXML
    public TextField tel;
    @FXML
    public TextField apellidos;
    @FXML
    public TextField cedula;
    @FXML
    public TextField saldoInicial;
    @FXML
    public DatePicker fechaVenCarne;
    @FXML
    public TextField mail;
    @FXML
    public TextField contraseña;
    @FXML
    public TextField saldoSobregiro;
    @FXML
    public Label etVariableMensajeError;



    public EmpresaRegistroEmplController() {
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

            boolean validos = chequeoDatos(nombresTxt,apellidosTxt,direccionTxt,telTxt,mailTxt,passwordTxt,cedula.getText());
            if (!validos) {
                abrirVentanaEmergenteError();
            }else {
                HttpResponse<String> responseCode = usuarioRest.guardarUsuario(mailTxt, passwordTxt, cedulaTxt, vencimientoCarne, nombresTxt, apellidosTxt, telTxt, saldoInicialNum, saldoSobregiroNum, direccionTxt);

                if (responseCode.getCode() == 409) { //este es el error especifico de que el usuario ya existe, tenemos q ver
                    //si puedo mostrar una variable en la pantalla que diga el mensaje? para no hacer n vistas distintas
                    abrirVentanaEmergenteError();
                } else if (responseCode.getCode() == 200) {
                    empresaController.volverAVistaEmpresa(actionEvent);
                    abrirVentanaEmergenteExito();
                }
            }

        } catch (NumberFormatException e){
            abrirVentanaEmergenteError();
        }
    }
    private boolean chequeoDatos(String nombresTxt, String apellidosTxt, String direccionTxt, String telTxt,
                             String mailTxt, String passwordTxt, String cedula) throws IOException {
        boolean sonValidos = true;
        if ( nombresTxt.isEmpty() || apellidosTxt.isEmpty() || direccionTxt.isEmpty() ||
                telTxt.isEmpty() || mailTxt.isEmpty() || passwordTxt.isEmpty() ||
                hasANumber(nombresTxt)|| hasANumber(apellidosTxt)|| (!isAnEmail(mailTxt)) || cedula.length()!=8 ){
            //mail: chequea q tenga un arroba, un punto algo, alguna letra mas, en minusculas, permite numeros y puntos
            sonValidos=false;

        }
        try{
            //cedula,saldos ya estan chequeados arriba cuando los parseo
            //direccion,contraseña chequeo?
            Integer.parseInt(telTxt);


        }catch (NumberFormatException e){
            sonValidos=false;
        }
        return sonValidos;
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
    @FXML
    public void salir(ActionEvent actionEvent) throws IOException {
        empresaController.salir(actionEvent);

    }

    @FXML
    private void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {
        empresaController.mostrarTablaEmpleados(actionEvent);
    }

    @FXML
    private void mostrarLiquidacion(ActionEvent actionEvent) {
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        empresaController.agregarCuenta(actionEvent);
    }
}
