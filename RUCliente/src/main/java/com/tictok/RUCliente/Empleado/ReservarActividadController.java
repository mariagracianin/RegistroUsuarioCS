package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
public class ReservarActividadController implements Initializable {
    @FXML
    public BorderPane root;
    private List<HorarioConCuposDTO> horariosConCupos;

    public GridPane contenedorHorarios;
    @FXML
    public Label nombreAct;
    @FXML
    public Label direccionAct;
    @FXML
    public Label precioAct;
    @FXML
    public Label lblNombreCentro;

    private  SuperActividadDTO estaActividad;
    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {//hacer request mery nombre centro, actividad, horariodto        int row=0;
        horariosConCupos = new ArrayList<>();
        try {
            horariosConCupos = obtenerHorarioConCupos();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        contenedorHorarios.getChildren().clear();
        int row=0;
        System.out.println("Cargando horarios");
        try {
            for (int i = 0; i < horariosConCupos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                HBox horarioBox = fxmlLoader.load(ReservarActividadController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/cardHorarioActividad.fxml"));

                CardHorarioActividadController cardHorarioController = fxmlLoader.getController();
                cardHorarioController.setDatosHorario(horariosConCupos.get(i));

                contenedorHorarios.add(horarioBox, 1, row++);
                //GridPane.setMargin(horarioBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<HorarioConCuposDTO> obtenerHorarioConCupos() throws JsonProcessingException {
        HttpResponse<String> response = centroDeportivoRest.obtenerActividadConCupos(estaActividad.getNombreCentro(),estaActividad.getNombreServicio());
        ObjectMapper objectMapper = new ObjectMapper();
        ActividadConHorariosYCuposDTO actividadConHorariosYCuposDTO = objectMapper.readValue(response.getBody(), ActividadConHorariosYCuposDTO.class);
        return actividadConHorariosYCuposDTO.getHorariosConCupos();
    }

    public void setDatos(SuperActividadDTO estaActividad){
        this.estaActividad = estaActividad;
        nombreAct.setText(estaActividad.getNombreServicio());
        direccionAct.setText(estaActividad.getAddress() +", " + estaActividad.getBarrio());
        precioAct.setText("Costo: $" + estaActividad.getPrecio());
        lblNombreCentro.setText("Centro Deportivo: "+ estaActividad.getNombreCentro());
    }

}
