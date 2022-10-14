package com.tictok.RUCliente.Empleado;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;

public class CardActividadController {

    @FXML
    private Label costo;

    @FXML
    private Label direccionYBarrio;

    @FXML
    private ImageView imagen;

    @FXML
    private VBox mainPane;

    @FXML
    private Label nombre;

    void setDatos(ActividadDTO actividad){
        //imageSrc seria la src dentro de mi computadora, tendria que ser en la base
        Image image = new Image(getClass().getResourceAsStream(actividad.getImageSrc()));
        imagen.setImage(image);
        nombre.setText(actividad.getNombreServicio());
        costo.setText("Costo: " + actividad.getPrecio());
        direccionYBarrio.setText(actividad.getCentroDeportivo());

    }
}
