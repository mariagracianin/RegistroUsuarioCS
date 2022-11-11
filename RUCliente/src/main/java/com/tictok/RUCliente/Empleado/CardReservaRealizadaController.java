package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.ReservaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CardReservaRealizadaController implements Initializable {
    public Label costoReserva;
    public Label nombreReserva;
    public Label horarioReserva;
    public Label centroReserva;
    public Label codReserva;
    public Label codReservaPadre;

    private ReservaDTO reservaSeleccionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void cancelarReserva(ActionEvent actionEvent) {
        //borrar de la tabla reservas con los datos de atributo reservaSeleccionada
    }

    public void setDatos(ReservaDTO reservaDTO) {
        reservaSeleccionada=reservaDTO;
        nombreReserva.setText(reservaDTO.getNombreActividad());
        costoReserva.setText(reservaDTO.getPrecio().toString());
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


        horarioReserva.setText(dia + horaInicioStr + " - "+ horaFinStr);
        centroReserva.setText(reservaDTO.getNombreCentro());
        codReserva.setText(reservaDTO.getCodigoReserva().toString());
        if (!(reservaDTO.getCodigoReservaPadre() == null)){
            codReservaPadre.setText("Código para unirse: "+ reservaDTO.getCodigoReservaPadre().toString());
        }
    }
}
