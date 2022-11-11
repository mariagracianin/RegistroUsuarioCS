package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.ActividadConHorariosYCuposDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UnirseReservaCanchaController implements Initializable {
    public TextField txtCodigo;

    @Autowired
    MiniCuenta miniCuenta;

    public Label costoReserva;
    public Label nombreReserva;
    public Label horarioReserva;
    public Label centroReserva;
    public Label codReserva;
    public Label codReservaPadre;

    public Long codigoPadrePosta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buscarReservaPadre(ActionEvent actionEvent) throws IOException {
        boolean existe = false;
        ReservaDTO reservaDTO = null;

        HttpResponse<String> response = CentroDeportivoRest.getReservaCanchaFromCodigo(txtCodigo.getText());
        if(response.getCode()==200) {
            ObjectMapper objectMapper = new ObjectMapper();
            reservaDTO = objectMapper.readValue(response.getBody(), ReservaDTO.class);
            existe = true;
        }else {
            //catrel de ERROR , no existe resreva con ese codigo
        }

        //buscar en la base de datos reserva con codigo txtCodigo.getText()
        //si no existe existe=false;
        if(existe){
            Node source = (Node)  actionEvent.getSource();
            Stage stageActual  = (Stage) source.getScene().getWindow();
            stageActual.close();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            SplitPane reserva = fxmlLoader.load(UnirseReservaCanchaController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/cardConfirmarUnirseReservaCancha.fxml"));

            UnirseReservaCanchaController cardController = fxmlLoader.getController();
            cardController.setDatos(reservaDTO);

            Stage stage = new Stage();
            Scene escena = new Scene(reserva);
            stage.setScene(escena);
            stage.setTitle("Reserva");
            escena.getStylesheets().add("/com/tictok/RUCliente/Empleado/actividad-cancha.css");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }

    }

    private void setDatos(ReservaDTO reservaDTO){
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
        codReservaPadre.setText("Código para unirse: "+ reservaDTO.getCodigoReserva().toString());
        codigoPadrePosta = reservaDTO.getCodigoReserva();

    }

    public void confirmarUnirse(ActionEvent actionEvent) {
        UsuarioRest.hacerReservaCanchaHija(codigoPadrePosta, miniCuenta);
        //aca si persistir la reserva agregandole un integrante (este usuario)
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
}
