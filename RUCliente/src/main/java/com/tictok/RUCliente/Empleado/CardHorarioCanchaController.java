package com.tictok.RUCliente.Empleado;

import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

//@Component
public class CardHorarioCanchaController implements Initializable {

    public Label lblEstado;
   // @Autowired
   // private UsuarioRest usuarioRest;

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

    private MiniCuenta miniCuenta;

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
        //horario.setCuposLibres(0);
        if (horario.getCuposLibres() != 0){
            lblCuposLibres.setText(""+horario.getCuposLibres());
            lblEstado.setText("Libre");
        }else{
            lblCuposLibres.setText("-");
            lblEstado.setText("Ocupada, únete con el código");
            btnAgregarHorario.setDisable(true);
        }

    }

    public void guardarReserva(ActionEvent actionEvent) {
        HorarioDTO horario = new HorarioDTO(horarioSeleccionado.getDia(),horarioSeleccionado.getHoraInicio(),horarioSeleccionado.getHoraFin());
        HttpResponse<String> response = UsuarioRest.hacerReserva(nombreCentro,nombreCancha,"Cancha",horario,null,this.miniCuenta);
        if(response.getCode()==200){
            btnAgregarHorario.setText("*");
        }else{
            throw new RuntimeException();
        }
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }
}
