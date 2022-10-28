package com.tictok.RUCliente.Centro;

import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.Empleado.InfoActSinReservaController;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardHorarioController implements Initializable {
    public Label lblDiaDeLaSemana;
    public Label lblHoraInicio;
    public Label lblHoraFin;
    private HorarioDTO horarioDTO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void eliminarHorario(ActionEvent actionEvent) throws IOException {
        CentroAgregarActController controller = (CentroAgregarActController) Main.getContext().getBean("centroAgregarActController");
        controller.eliminarHorario(this.horarioDTO);

    }

    public void setLabels(HorarioDTO horario){
        this.horarioDTO = horario;
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
        int horaInicio = horario.getHoraInicio();
        int hora = horaInicio/100;
        String horaInicioStr = String.valueOf(hora) + ":" + String.valueOf(horaInicio-hora*100);

        lblHoraInicio.setText(horaInicioStr);
        int horaFin = horario.getHoraFin();
        int horaF = horaFin/100;
        String horaFinStr = String.valueOf(horaF) + ":" + String.valueOf(horaFin-horaF*100);

        lblHoraFin.setText(horaFinStr);
    }
}
