package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CentroDeportivoDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CentroCheckInUsuarioReservasController implements Initializable {
    @Autowired
    MiniCuenta miniCuenta;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    public GridPane contenedorReservas;
    private int cedulaUsuario;
    private List<ReservaDTO> reservas;

    @Autowired
    CentroController centroController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            reservas = getReservasDeUsuario(this.cedulaUsuario);
            System.out.println("--------------------------- reservas size" + reservas.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
                cardController.setCedula(this.cedulaUsuario);
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

    private List<ReservaDTO> getReservasDeUsuario(int cedula) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse<String> response = centroDeportivoRest.buscarReservasFromUsuario(cedula);
        List<ReservaDTO> list = objectMapper.readValue(response.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, ReservaDTO.class));
        return list;
    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        centroController.verCanchas(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public void volverAtras(ActionEvent actionEvent) throws IOException {
        centroController.irACheckIn(actionEvent);
    }

    public void salirVentanasEmergentes(ActionEvent actionEvent) {
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        centroController.agregarCuenta(actionEvent);
    }
}
