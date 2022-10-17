package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.MiniCuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Component
public class EmpMisDatosController implements Initializable {

    @Autowired
    EmpleadoController empleadoController;

    public Label lblNombre;
    public Label lblApellido;
    public Label lblCedula;
    public Label lblMail;
    public Label lblDireccion;
    public Label lblTel;
    public Label lblSaldoActual;
    public Label lblSobregiro;
    public Label lblFechaVenCarne;
    private UsuarioDTO usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblNombre.setText(usuario.getNombre());
        //actualizar las labels con la info del usuario, a partir de la minicuenta que tengo hacer request
    }
    public void setUsuario(MiniCuentaDTO minicuenta){
        //hacer request, y this.usuario = usuario de la request
    }


    public void verReservas(ActionEvent actionEvent) {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empleadoController.verCanchas(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
