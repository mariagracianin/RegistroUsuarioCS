package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ReservarCanchaController implements Initializable {

    public GridPane contenedorHorarios;
    private List<HorarioDTO> horariosCancha;
    //labels de horario
    public Label lblDiaDeLaSemana;
    public Label lblHoraInicio;
    public Label lblHoraFin;
    public Label lblCuposLibres;
    public Button btnAgregarHorario;
    //
    public Label nombreCan;
    public Label precioCan;
    public Label lblNombreCentro;
    public Label direccionCan;

    private SuperCanchaDTO estaCancha;

    public void setEstaCancha(SuperCanchaDTO estaCancha) {
        this.estaCancha = estaCancha;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        horariosCancha= new ArrayList<>();
        contenedorHorarios.getChildren().clear();
        horariosCancha.addAll(this.estaCancha.getHorarios());
        int row=0;
        try {
            for (int i=0; i<horariosCancha.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardHorarioCancha.fxmll"));
                SplitPane actBox = fxmlLoader.load();

                CardHorarioCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosHorario(horariosCancha.get(i));

                contenedorHorarios.add(actBox,1,row++);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
