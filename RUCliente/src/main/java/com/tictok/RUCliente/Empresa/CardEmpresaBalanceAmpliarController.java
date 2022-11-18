package com.tictok.RUCliente.Empresa;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CardEmpresaBalanceAmpliarController implements Initializable {
    public Label lblActividad;
    public Label lblFecha;
    public Label lblPrecio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setData(String nombreAct, String fecha, String precio ){
        lblActividad.setText(nombreAct);
        lblFecha.setText(fecha);
        lblPrecio.setText(precio);

    }
}
