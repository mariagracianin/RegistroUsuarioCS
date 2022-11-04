package com.tictok.RUCliente.Centro;

import com.tictok.Commons.ReservaDTO;
import com.tictok.RUCliente.Empleado.CardReservaRealizadaController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

@Component
public class CentroCheckInUsuarioReservasController implements Initializable {
    @Autowired
    MiniCuenta miniCuenta;

    public GridPane contenedorReservas;
    private int cedulaUsuario;

    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ReservaDTO> reservas= getReservasDeUsuario(this.cedulaUsuario);
        contenedorReservas.getChildren().clear();
        int column=0;
        int row=0;

        try {
            for (int i=0; i<reservas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Centro/cardCheckInConReserva.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCheckInConReservaController  cardController = fxmlLoader.getController();
                cardController.setDatos(reservas.get(i),this.cedulaUsuario);
                cardController.setMiniCuenta(miniCuenta);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                contenedorReservas.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private ArrayList<ReservaDTO> getReservasDeUsuario(int cedula){

        //llamar funcion mery que me devuelva la lista con las reservas de usuario a partir de la cedula
        return null;
    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
}
