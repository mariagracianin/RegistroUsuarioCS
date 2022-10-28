package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.HorarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

//@Component
public class CardHorarioCanchaController implements Initializable {
    private HorarioConCuposDTO horarioSeleccionado;
    private String nombreCancha;
    private String nombreCentro;

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
    void setDatosHorario(HorarioConCuposDTO horario){
        horarioSeleccionado=horario;

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
       // horario.setCuposLibres(0);
        if (horario.getCuposLibres() == 1){
            lblCuposLibres.setText("Libre");
        }else{
            lblCuposLibres.setText("Ocupada, únete con el código");
            btnAgregarHorario.setDisable(true);
        }

    }

    public void guardarReserva(ActionEvent actionEvent) {
        btnAgregarHorario.setText("*");
        System.out.println(horarioSeleccionado.getHoraInicio());
        System.out.println(nombreCancha);
        System.out.println(nombreCentro);
       //
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }
}
