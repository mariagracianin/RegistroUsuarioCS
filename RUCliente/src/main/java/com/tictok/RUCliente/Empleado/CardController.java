package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

@Component
public class CardController {

    @FXML
    private Label costo;

    @FXML
    private Label direccionYBarrio;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombre;

    void setDatosActividad(SuperActividadDTO actividad){
        //imageSrc seria la src dentro de mi computadora, tendria que ser en la base
        Image image = new Image(getClass().getResourceAsStream(actividad.getImageString()));
        imagen.setImage(image);
        nombre.setText(actividad.getNombreServicio());
        costo.setText("Costo: " + actividad.getPrecio());
        direccionYBarrio.setText(actividad.getAddress() +", "+ actividad.getBarrio());

    }
    void setDatosCancha(SuperCanchaDTO cancha){
        Image image = new Image(getClass().getResourceAsStream(cancha.getImageString()));
        imagen.setImage(image);
        nombre.setText(cancha.getNombreServicio());
        costo.setText("Costo: "+ cancha.getPrecio());
        direccionYBarrio.setText(cancha.getAddress() +", "+ cancha.getBarrio());
    }

    public void guardarReserva(ActionEvent actionEvent) {
    }
}
