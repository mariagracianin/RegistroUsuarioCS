package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardCanchaController {

    @FXML
    private Label costo;

    @FXML
    private Label direccionYBarrio;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombre;
    private SuperCanchaDTO cancha;


    void setDatosCancha(SuperCanchaDTO cancha) {
        this.cancha = cancha;
        Image image = new Image(getClass().getResourceAsStream(cancha.getImageSrc()));
        imagen.setImage(image);
        nombre.setText(cancha.getNombreServicio());
        costo.setText("Costo: " + cancha.getPrecio());
        direccionYBarrio.setText(cancha.getAddress() + ", " + cancha.getBarrio());
    }


    public void abrirVentanaReservaConDatos(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(CardCanchaController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/reservaCancha.fxml"));

        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        ReservarCanchaController reservarCanchaController = fxmlLoader.getController();
        reservarCanchaController.setEstaCancha(this.cancha);
        stage.show();
    }
}