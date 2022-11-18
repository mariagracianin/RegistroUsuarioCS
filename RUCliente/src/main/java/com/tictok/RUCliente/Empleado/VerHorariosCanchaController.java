package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class VerHorariosCanchaController implements Initializable {

    public GridPane contenedorHorarios;
    private List<HorarioConCuposDTO> horariosConCuposCancha;

    public Label nombreCan;
    public Label precioCan;
    public Label lblNombreCentro;
    public Label direccionCan;

    private SuperCanchaDTO estaCancha;
    private MiniCuenta miniCuenta;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    public void setEstaCancha(SuperCanchaDTO estaCancha) {
        this.estaCancha = estaCancha;
    }

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabelsCancha();
        horariosConCuposCancha = new ArrayList<>();
        try {
            horariosConCuposCancha = obtenerHorarioConCupos();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        contenedorHorarios.getChildren().clear();
        int row=0;
        try {
            for (int i = 0; i< horariosConCuposCancha.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardHorarioCancha.fxml"));
                HBox horarioBox = fxmlLoader.load();

                CardHorarioCanchaController cardController = fxmlLoader.getController();
                cardController.setMiniCuenta(miniCuenta);
                cardController.setNombreCancha(estaCancha.getNombreServicio());
                cardController.setNombreCentro(estaCancha.getNombreCentro());
                cardController.setDatosHorario(horariosConCuposCancha.get(i));

                contenedorHorarios.add(horarioBox,1,row++);
                GridPane.setMargin(horarioBox, new Insets(5));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<HorarioConCuposDTO> obtenerHorarioConCupos() throws JsonProcessingException {
        HttpResponse<String> response = centroDeportivoRest.obtenerCanchaConCupos(estaCancha.getNombreCentro(),estaCancha.getNombreServicio());
        ObjectMapper objectMapper = new ObjectMapper();
        CanchaConHorariosYCuposDTO canchaConHorariosYCuposDTO = objectMapper.readValue(response.getBody(), CanchaConHorariosYCuposDTO.class);
        return canchaConHorariosYCuposDTO.getHorariosConCupos();
    }

    public void setLabelsCancha(){
        nombreCan.setText(estaCancha.getNombreServicio());
        direccionCan.setText(estaCancha.getAddress() +", " + estaCancha.getBarrio());
        precioCan.setText("Costo: $" + estaCancha.getPrecio());
        lblNombreCentro.setText("Centro Deportivo: "+ estaCancha.getNombreCentro());
    }


}
