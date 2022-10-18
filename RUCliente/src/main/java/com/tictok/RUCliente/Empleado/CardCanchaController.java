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
    private MyListenerCan listenerCan;

    @FXML
    private void click(MouseEvent mouseEvent){
        listenerCan.onClickListenerCan(cancha);
    }

    void setDatosCancha(SuperCanchaDTO cancha, MyListenerCan listenerCan){
        this.listenerCan = listenerCan;
        this.cancha = cancha;
        Image image = new Image(getClass().getResourceAsStream(cancha.getImageSrc()));
        imagen.setImage(image);
        nombre.setText(cancha.getNombreServicio());
        costo.setText("Costo: "+ cancha.getPrecio());
        direccionYBarrio.setText(cancha.getAddress() +", "+ cancha.getBarrio());
    }

    public void guardarReserva(ActionEvent actionEvent) {
        listenerCan = new MyListenerCan() {
            @Override
            public void onClickListenerCan(SuperCanchaDTO can) {
                //setear todas las label e info con la de can
                System.out.println("clickeaste "+ can.getNombreServicio());
            }
        };
        //cargar vista reserva en ventana emergente, mismo controller y hacer coso horarios y boton reservar
    }
}
