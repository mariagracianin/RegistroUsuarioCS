package com.tictok.RUCliente.Centro;

import com.tictok.Commons.ActividadConHorariosYCuposDTO;
import com.tictok.Commons.CanchaConHorariosYCuposDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

public class CardCheckInConReservaController implements Initializable {
    
    public Label codReserva;
    public Label diaHorarioReserva;
    public Label costoReserva;
    public Label nombreReserva;
    public Button btnCheckIn;
    private ReservaDTO reservaSeleccionada;
    private int cedulaUsuario;
    private MiniCuenta miniCuenta; //del centro

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDatos(ReservaDTO reservaDTO, int cedula) {
        cedulaUsuario=cedula;
        reservaSeleccionada=reservaDTO;
        nombreReserva.setText(reservaDTO.getNombreActividad());
       // costoReserva.setText(reservaDTO.getPrecio().toString());
        String dia = "";
        switch (reservaDTO.getHorario().getDia()) {
            case 1 -> dia = "Lunes, ";
            case 2 -> dia = "Martes, ";
            case 3 -> dia = "Miércoles, ";
            case 4 -> dia = "Jueves, ";
            case 5 -> dia = "Viernes, ";
            case 6 -> dia = "Sábado, ";
            case 7 -> dia = "Domingo, ";
        }

        int horaInicio = reservaDTO.getHorario().getHoraInicio();
        int hora = horaInicio/100;
        String horaInicioStr = String.valueOf(hora) + ":" + String.valueOf(horaInicio-hora*100);

        int horaFin = reservaDTO.getHorario().getHoraFin();
        int horaF = horaFin/100;
        String horaFinStr = String.valueOf(horaF) + ":" + String.valueOf(horaFin-horaF*100);


        diaHorarioReserva.setText(dia + horaInicioStr + " - "+ horaFinStr);
        // direccionReserva.setText(reservaDTO.get)
        codReserva.setText(reservaDTO.getCodigoReserva().toString());
    }

    public void ingresarCheckIn(ActionEvent actionEvent) {
        //post check in
        btnCheckIn.setDisable(true);
    }
}
