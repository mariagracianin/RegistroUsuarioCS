package com.tictok.RUCliente.Empleado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpActividadesController implements Initializable {
    @Autowired
    EmpleadoController empleadoController;

    @FXML
    private GridPane contenedorAct;
    private List<ActividadDTO> actividadesActuales;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //actividadesActuales = actualizar;
        int column=0;
        int row=1;
        try {
        for (ActividadDTO act : actividadesActuales){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardActividad.fxml"));

                VBox actBox = fxmlLoader.load();
            CardActividadController cardController = fxmlLoader.getController();
            cardController.setDatos(act);

            if (column ==4){
                column=0;
                row++;
            }
            contenedorAct.add(actBox,column++,row);
            GridPane.setMargin(actBox, new Insets(10));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void verReservas(ActionEvent actionEvent) {
        empleadoController.verReservas(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        empleadoController.verCanchas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) {
        empleadoController.verDatos(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
