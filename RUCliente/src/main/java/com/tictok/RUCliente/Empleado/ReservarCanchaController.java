package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
public class ReservarCanchaController implements Initializable {

    public GridPane contenedorHorarios;
    private List<HorarioConCuposDTO> horariosConCuposCancha;

    public Label nombreCan;
    public Label precioCan;
    public Label lblNombreCentro;
    public Label direccionCan;

    private SuperCanchaDTO estaCancha;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    public void setEstaCancha(SuperCanchaDTO estaCancha) {
        this.estaCancha = estaCancha;
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
        horariosConCuposCancha.addAll(horariosConCuposCancha);
        int row=0;
        try {
            for (int i = 0; i< horariosConCuposCancha.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardHorarioCancha.fxmll"));
                SplitPane actBox = fxmlLoader.load();

                CardHorarioCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosHorario(horariosConCuposCancha.get(i));

                contenedorHorarios.add(actBox,1,row++);
                GridPane.setMargin(actBox, new Insets(10));

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
