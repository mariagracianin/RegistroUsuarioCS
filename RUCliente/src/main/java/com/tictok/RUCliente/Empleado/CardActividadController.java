package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.SuperActividadDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class CardActividadController {

    @FXML
    private Label costo;

    @FXML
    private Label direccionYBarrio;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombre;
    private SuperActividadDTO actividad;

    void setDatos(SuperActividadDTO actividad){
        //imageSrc seria la src dentro de mi computadora, tendria que ser en la base
        this.actividad=actividad;
        Image image = new Image(getClass().getResourceAsStream(actividad.getImageSrc()));
        imagen.setImage(image);
        nombre.setText(actividad.getNombreServicio());
        costo.setText("Costo: " + actividad.getPrecio());
        direccionYBarrio.setText(actividad.getAddress() +", "+ actividad.getBarrio());

    }

    public void guardarReserva(ActionEvent actionEvent) {
    }
}
