package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Empleado.CardHorarioActividadController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
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

public class VerHorariosCheckInSinReservaController implements Initializable {
    @FXML
    public BorderPane root;
    public Label nombreServicio;
    public Label precioServicio;
    public Label lblServicio;
    private List<HorarioConCuposDTO> horariosConCupos;
    private MiniCuenta miniCuenta;
    public GridPane contenedorHorarios;

    private SuperActividadDTO actSeleccionada = null;
    private SuperCanchaDTO canSeleccionada = null;
    private int cedulaUsuario;

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

    public VerHorariosCheckInSinReservaController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {//hacer request mery nombre centro, actividad, horariodto

    }
    public void cargarHorarios(){
        if (actSeleccionada != null){
            lblServicio.setText("Actividad");
            nombreServicio.setText(actSeleccionada.getNombreServicio());
            precioServicio.setText("Costo: "+ actSeleccionada.getPrecio());
        }else if (canSeleccionada != null){
            lblServicio.setText("Cancha");
            nombreServicio.setText(canSeleccionada.getNombreServicio());
            precioServicio.setText("Costo: "+ canSeleccionada.getPrecio());
        }

        horariosConCupos = new ArrayList<>();
        try {
            horariosConCupos = obtenerHorarioConCupos();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        contenedorHorarios.getChildren().clear();
        int row=0;
        try {
            for (int i = 0; i < horariosConCupos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Centro/cardHorarioCheckIn.fxml"));
                HBox horarioBox = fxmlLoader.load();

                CardHorarioCheckInController cardHorarioController = fxmlLoader.getController();
                if (actSeleccionada != null){
                    cardHorarioController.setNombreActividad(actSeleccionada.getNombreServicio());
                }else if (canSeleccionada != null){
                    cardHorarioController.setNombreCancha(canSeleccionada.getNombreServicio());
                }
                cardHorarioController.setCedulaUsuario(this.cedulaUsuario);
                cardHorarioController.setDatosHorario(horariosConCupos.get(i));
                cardHorarioController.setMiniCuenta(miniCuenta);

                contenedorHorarios.add(horarioBox, 1, row++);
                //GridPane.setMargin(horarioBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<HorarioConCuposDTO> obtenerHorarioConCupos() throws JsonProcessingException {
        if (actSeleccionada != null){
            HttpResponse<String> response = CentroDeportivoRest.obtenerActividadConCupos(actSeleccionada.getNombreCentro(),actSeleccionada.getNombreServicio());
            ObjectMapper objectMapper = new ObjectMapper();
            ActividadConHorariosYCuposDTO actividadConHorariosYCuposDTO = objectMapper.readValue(response.getBody(), ActividadConHorariosYCuposDTO.class);
            return actividadConHorariosYCuposDTO.getHorariosConCupos();
        }else if (canSeleccionada != null){
            HttpResponse<String> response = CentroDeportivoRest.obtenerCanchaConCupos(canSeleccionada.getNombreCentro(),canSeleccionada.getNombreServicio());
            ObjectMapper objectMapper = new ObjectMapper();
            CanchaConHorariosYCuposDTO canchaConHorariosYCuposDTO = objectMapper.readValue(response.getBody(), CanchaConHorariosYCuposDTO.class);
            return canchaConHorariosYCuposDTO.getHorariosConCupos();
        }
        return null;
    }

    public void setActividad(SuperActividadDTO estaActividad){
        this.actSeleccionada = estaActividad;
    }
    public void setCancha(SuperCanchaDTO estaCancha){
        this.canSeleccionada = estaCancha;
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
}
