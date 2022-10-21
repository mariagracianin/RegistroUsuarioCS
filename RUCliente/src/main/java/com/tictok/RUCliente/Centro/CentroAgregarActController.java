package com.tictok.RUCliente.Centro;

import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentroAgregarActController implements Initializable {
    public TextField txtPrecio;
    public TextField txtCupos;
    public TextField txtNombre;
    public RadioButton paseLibre;

    @Autowired
    CentroController centroController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paseLibre.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(paseLibre.isSelected()){
                    txtCupos.setDisable(true);
                }
            }
        });
    }


    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void guardarDatos(ActionEvent actionEvent){
        if (txtCupos.isDisable()){
            //centroDeportivoRest.guardarActividad(txtNombre.getText(),float)
            //mandar datos con pase libre = true
            //sin horarios

        }else{
            //mandar datos con pase libre = false y lista de horarios
        }
    }
}
