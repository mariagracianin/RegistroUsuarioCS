package com.tictok.RUCliente.Empleado;

import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//@Component
public class CardHorarioActividadController implements Initializable {
    public Label etVariableMensajeError;
    private HorarioConCuposDTO horarioSeleccionado;
    private String nombreActividad;
    private String nombreCentro;
    private MiniCuenta miniCuenta;

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

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

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
        lblCuposLibres.setText(horario.getCuposLibres()+"");
    }
    @FXML
    public void guardarReservaFinal(ActionEvent actionEvent) throws IOException {
        HorarioDTO horario = new HorarioDTO(horarioSeleccionado.getDia(),horarioSeleccionado.getHoraInicio(),horarioSeleccionado.getHoraFin());
        //System.out.println(horarioSeleccionado.getDia() + "   " +  horarioSeleccionado.getHoraInicio() + "   " +  horarioSeleccionado.getHoraFin() +"   " +  horarioSeleccionado.getCuposLibres());
        HttpResponse<String> response = UsuarioRest.hacerReserva(nombreCentro,nombreActividad,"Actividad",horario,null,this.miniCuenta);
        if(response.getCode()==200){
            btnAgregarHorario.setText("*");
            btnAgregarHorario.setDisable(true);

        }else{
            throw new RuntimeException();
        }
    }

    public void setNombreActividad(String nombreServicio) {
        this.nombreActividad = nombreServicio;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }


}
