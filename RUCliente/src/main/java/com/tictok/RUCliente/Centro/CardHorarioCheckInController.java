package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.HorarioConCuposDTO;
import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Empleado.CardHorarioActividadController;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardHorarioCheckInController implements Initializable {
    private HorarioConCuposDTO horarioSeleccionado;
    public Label lblDiaDeLaSemana;
    public Label lblHoraInicio;
    public Label lblHoraFin;
    public Label lblCuposLibres;
    public Button btnCheckIn;
    private int cedulaUsuario;
    private MiniCuenta miniCuenta; //del centro
    private String nombreActividad;
    private String nombreCancha;

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

    public void setNombreActividad(String nombreServicio) {
        this.nombreActividad = nombreServicio;
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void hacerCheckInHorario(ActionEvent actionEvent) throws IOException {
        HorarioDTO horarioDTO = new HorarioDTO(horarioSeleccionado.getDia(),horarioSeleccionado.getHoraInicio(),horarioSeleccionado.getHoraFin());

        HttpResponse<String> response = null;
        if(nombreCancha ==  null){
            response = CentroDeportivoRest.hacerCheckInSinReserva(cedulaUsuario,nombreActividad,"Actividad", horarioDTO,null, miniCuenta);
        }
        
        if (response.getCode() == 200){
            btnCheckIn.setDisable(true);
            
        }else if (response.getCode() == 403) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
    
            Parent root = fxmlLoader.load(CardHorarioCheckInController.class.getResourceAsStream("ventSaldoInsuficienteCheckIn.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
    
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }else if (response.getCode() == 405) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(CardHorarioCheckInController.class.getResourceAsStream("ventCarnetVencidoCheckIn.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }else{
            throw new RuntimeException();
        }
        
        
        
    }

    public void salirVentanasEmergentes(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
}
