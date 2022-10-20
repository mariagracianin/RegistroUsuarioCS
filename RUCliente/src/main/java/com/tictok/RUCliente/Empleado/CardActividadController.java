package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Empresa.EmpresaController;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CardActividadController implements Initializable {
    @FXML
    public SplitPane pane;
    @FXML
    private Label costo;

    @FXML
    private Label direccionYBarrio;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombre;
    private SuperActividadDTO actividadSeleccionada;

    public void setDatosActividad(SuperActividadDTO actividad){
        actividadSeleccionada=actividad;
        //imageSrc seria la src dentro de mi computadora, tendria que ser en la base

        //byte[] decodedBytes = Base64.getDecoder().decode(actividad.getImageSrc());
        //ByteArrayInputStream i = new ByteArrayInputStream(decodedBytes);
        Image image = new Image(getClass().getResourceAsStream(actividad.getImageSrc()));
       // imagen.setImage(new Image(i));
        imagen.setImage(image);

        nombre.setText(actividad.getNombreServicio());
        costo.setText("Costo: " + actividad.getPrecio());
        direccionYBarrio.setText(actividad.getAddress() +", "+ actividad.getBarrio());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void abrirVentanaReservaConDatos(ActionEvent actionEvent) throws IOException {
        System.out.println(actividadSeleccionada.getNombreServicio());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(CardActividadController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/reservaActividad.fxml"));

        ReservarActividadController controller = fxmlLoader.getController();
        controller.setEstaActividad(actividadSeleccionada);

        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.show();
    }

}
