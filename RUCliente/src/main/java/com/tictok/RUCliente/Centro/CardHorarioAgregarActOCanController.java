package com.tictok.RUCliente.Centro;

import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardHorarioAgregarActOCanController implements Initializable {
    public Label lblDiaDeLaSemana;
    public Label lblHoraInicio;
    public Label lblHoraFin;
    private HorarioDTO horarioDTO;
    private String tipo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void eliminarHorario(ActionEvent actionEvent) throws IOException {

        if (tipo.equals("actividad")) {
            CentroAgregarActController controller = (CentroAgregarActController) Main.getContext().getBean("centroAgregarActController");
            controller.eliminarHorario(this.horarioDTO);
        } else if (tipo.equals("cancha")){
            CentroAgregarCanController controller = (CentroAgregarCanController) Main.getContext().getBean("centroAgregarCanController");
            controller.eliminarHorario(this.horarioDTO);
        }


    }

    public void setLabels(HorarioDTO horario, String tipo){
        this.tipo = tipo;
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

        String horaInicioStr;
        if ((horaInicio-hora*100)<10) {
            horaInicioStr = hora + ":0" + String.valueOf(horaInicio - hora * 100);
        } else {
            horaInicioStr = hora + ":" + String.valueOf(horaInicio - hora * 100);
        }
        lblHoraInicio.setText(horaInicioStr);
        int horaFin = horario.getHoraFin();
        int horaF = horaFin/100;

        String horaFinStr;
        if ((horaFin-horaF*100)<10) {
            horaFinStr = horaF + ":0" + (horaFin - horaF * 100);
        } else {
            horaFinStr = horaF + ":" + (horaFin - horaF * 100);
        }

        lblHoraFin.setText(horaFinStr);
    }
}