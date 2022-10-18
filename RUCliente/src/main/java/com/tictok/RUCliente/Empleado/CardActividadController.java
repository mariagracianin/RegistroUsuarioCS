package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;

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
    private MyListenerAct listenerAct;

    @FXML
    private void click(MouseEvent mouseEvent){
        listenerAct.onClickListenerAct(actividad);
    }

    void setDatosActividad(SuperActividadDTO actividad, MyListenerAct listenerAct){
        this.listenerAct = listenerAct;
        this.actividad =actividad;
        //imageSrc seria la src dentro de mi computadora, tendria que ser en la base
        Image image = new Image(getClass().getResourceAsStream(actividad.getImageSrc()));
        imagen.setImage(image);
        nombre.setText(actividad.getNombreServicio());
        costo.setText("Costo: " + actividad.getPrecio());
        direccionYBarrio.setText(actividad.getAddress() +", "+ actividad.getBarrio());

    }

    public void guardarReserva(ActionEvent actionEvent) {
        listenerAct = new MyListenerAct() {
            @Override
            public void onClickListenerAct(SuperActividadDTO act) {
                //setear las labels e info con la info de act
                System.out.println("clickeaste "+ act.getNombreServicio());
            }
        };
        //cargar vista reserva en ventana emergente, mismo controller y hacer coso horarios y boton reservar
    }
}
