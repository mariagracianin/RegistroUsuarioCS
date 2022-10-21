package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.apache.camel.reifier.ResequenceReifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpMisReservasController implements Initializable {

   // @Autowired
   // CardReservaRealizadaController cardReservaRealizadaController;

    @Autowired
    EmpleadoController empleadoController;

    public GridPane contenedorReservas;
    private  List<ReservaDTO> reservas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservas= new ArrayList<>();
        contenedorReservas.getChildren().clear();
        reservas.addAll(getDatos());
        int row=0;

        try {
            for (int i=0; i<reservas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                SplitPane actBox = fxmlLoader.load(EmpMisReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/cardReserva.fxml"));

                CardReservaRealizadaController cardController = fxmlLoader.getController();
                cardController.setDatos(reservas.get(i));
               // cardReservaRealizadaController.setDatos(reservas.get(i));

                contenedorReservas.add(actBox,0,row++);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<ReservaDTO> getDatos(){
        List<ReservaDTO> l = new ArrayList<>();
        ReservaDTO r1 = new ReservaDTO(12345678,"Bigua","Tenis","tipo?",new HorarioDTO(1,1330,1430));
        r1.setCodigoReserva(1L);
        r1.setCodigoReservaPadre(2L);
        l.add(r1);
        //devolver lista con las reservas de este usuario q esten activas hasta la fecha
        //o en un principio devolver todas

        return l;
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empleadoController.verDatos(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empleadoController.verCanchas(actionEvent);
    }
}
