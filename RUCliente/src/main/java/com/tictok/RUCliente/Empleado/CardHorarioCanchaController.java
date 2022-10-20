package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CardHorarioCanchaController implements Initializable {
    private HorarioDTO horarioSeleccionado;
    @FXML
    public Label lblDiaDeLaSemana;
    @FXML
    public Label lblHoraInicio;
    @FXML
    public Label lblHoraFin;
    @FXML
    public Label lblCuposLibres;
    @FXML
    public Button btnAgregarHorario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    void setDatosHorario(HorarioDTO horario){
        this.horarioSeleccionado=horario;
        if (horario.getDia()==1){
            lblDiaDeLaSemana.setText("Lunes");
        }else if(horario.getDia()==2){
            lblDiaDeLaSemana.setText("Martes");
        }else if (horario.getDia()==3){
            lblDiaDeLaSemana.setText("Miércoles");
        }else if (horario.getDia()==4){
            lblDiaDeLaSemana.setText("Jueves");
        }else if (horario.getDia()==5){
            lblDiaDeLaSemana.setText("Viernes");
        }else if (horario.getDia()==6){
            lblDiaDeLaSemana.setText("Sábado");
        }else{
            lblDiaDeLaSemana.setText("Domingo");
        }
        //pasar los horarios en int a horarios normales en string

        int horaInicio = horario.getHoraInicio();
        int hora = horaInicio/100;
        String horaInicioStr = String.valueOf(hora) + ":" + String.valueOf(horaInicio-hora*100);

        lblHoraInicio.setText(horaInicioStr);
        int horaFin = horario.getHoraFin();
        int horaF = horaFin/100;
        String horaFinStr = String.valueOf(horaF) + ":" + String.valueOf(horaFin-horaF*100);

        lblHoraFin.setText(horaFinStr);
        //falta lblCuposLibres.setText(horario.getCupos()); o hacer la cuenta de los libres si esos son los totales

    }

    public void guardarReserva(ActionEvent actionEvent) {
        btnAgregarHorario.setText("*");
        //mandar request, si ya habia un dueño de la reserva salta ventana emergente para introducir codigo
        //(cedula) y si coincide se realiza la reserva, sino no
        //si no habia dueño de la reserva se realiza la reserva ok
    }
}
