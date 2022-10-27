package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.SuperActividadDTO;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InfoActSinReservaController implements Initializable {
    public Label nombreAct;
    public Label precioAct;
    public Label lblNombreCentro;
    public Label direccionAct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDatos(SuperActividadDTO actividadSeleccionada) {
        nombreAct.setText(actividadSeleccionada.getNombreServicio());
        direccionAct.setText(actividadSeleccionada.getAddress() +", " + actividadSeleccionada.getBarrio());
        precioAct.setText("Costo: $" + actividadSeleccionada.getPrecio());
        lblNombreCentro.setText("Centro Deportivo: "+ actividadSeleccionada.getNombreCentro());
    }
}
