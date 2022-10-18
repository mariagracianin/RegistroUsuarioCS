package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.SuperActividadDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpActividadesController implements Initializable {
    @Autowired
    EmpleadoController empleadoController;

    @FXML
    private GridPane contenedorAct;
    private List<SuperActividadDTO> actividadesActuales= new ArrayList<>();
    private MyListenerAct listenerAct;

    private List<SuperActividadDTO> getDatos(){
        //aca mery me devolveria la lista de superactividades q tengo q mostrar al usuario
        /*List<SuperActividadDTO> actividades= new ArrayList<>();
        HorarioConCuposDTO h1 = new HorarioConCuposDTO(3,13,14,15);
        HorarioConCuposDTO h2 = new HorarioConCuposDTO(4,13,14,-1);
        HorarioConCuposDTO h3 = new HorarioConCuposDTO(3,13,14,10);
        ArrayList<HorarioConCuposDTO> h = new ArrayList<HorarioConCuposDTO>();
        h.add(h1);
        h.add(h2);
        h.add(h3);

        SuperActividadDTO a1= new SuperActividadDTO("Yoga", 500, false, "Club Bigua","direc","Pocitos","12345678",h, "/com/tictok/RUCliente/GETFITlogin.png");
        SuperActividadDTO a2= new SuperActividadDTO("GYM", 400, true, "ACJ","direc","Centro","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");
        SuperActividadDTO a3= new SuperActividadDTO("Natacion", 500, false, "ACJ","direc","Punta Carretas","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");

        actividades.add(a1);
        actividades.add(a2);
        actividades.add(a3);
        actividades.add(a1);

        return actividades;*/
        return null;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actividadesActuales.addAll(getDatos());
        int column=0;
        int row=0;
        try {
        for (int i=0; i<actividadesActuales.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("cardActividad.fxml"));
            SplitPane actBox = fxmlLoader.load();

            CardActividadController cardController = fxmlLoader.getController();
            cardController.setDatosActividad(actividadesActuales.get(i),listenerAct);
            if (column == 3) {
                column = 0;
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

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empleadoController.verCanchas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empleadoController.verDatos(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
