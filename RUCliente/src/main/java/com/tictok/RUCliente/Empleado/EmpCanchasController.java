package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.SuperCanchaDTO;
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
public class EmpCanchasController implements Initializable {
    @Autowired
    EmpleadoController empleadoController;
    @FXML
    private GridPane contenedorCanchas;
    private List<SuperCanchaDTO> canchasActuales= new ArrayList<>();
    private MyListenerCan listenerCan;

    private List<SuperCanchaDTO> getDatos(){
        //aca mery me devolveria la lista de supercanchas q tengo q mostrar al usuario
        List<SuperCanchaDTO> canchas= new ArrayList<>();
        HorarioConCuposDTO h1 = new HorarioConCuposDTO(3,13,14,15);
        HorarioConCuposDTO h2 = new HorarioConCuposDTO(4,13,14,-1);
        HorarioConCuposDTO h3 = new HorarioConCuposDTO(3,13,14,10);
        ArrayList<HorarioConCuposDTO> h = new ArrayList<HorarioConCuposDTO>();
        h.add(h1);
        h.add(h2);
        h.add(h3);

        SuperCanchaDTO a1= new SuperCanchaDTO("Futbol", 500, "Club Bigua","direc","Pocitos","12345678",h, "/com/tictok/RUCliente/GETFITlogin.png");
        SuperCanchaDTO a2= new SuperCanchaDTO("Basquet", 400, "ACJ","direc","Centro","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");
        SuperCanchaDTO a3= new SuperCanchaDTO("Natacion", 500, "ACJ","direc","Punta Carretas","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");

        canchas.add(a1);
        canchas.add(a2);
        canchas.add(a3);
        canchas.add(a1);

        return canchas;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canchasActuales.addAll(getDatos());
        int column=0;
        int row=0;
        try {
            for (int i=0; i<canchasActuales.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardActividad.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosCancha(canchasActuales.get(i), listenerCan);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorCanchas.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void verReservas(ActionEvent actionEvent) {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
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
