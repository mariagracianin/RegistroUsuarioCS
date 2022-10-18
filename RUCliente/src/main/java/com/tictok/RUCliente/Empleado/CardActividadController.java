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
import org.springframework.stereotype.Component;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CardActividadController implements Initializable {

    public Label nombreAct;
    public Label direccionAct;
    public Label precioAct;
    public GridPane contenedorHorarios;
    public Button btnConfirmarReserva;

    public Label lblDiaDeLaSemana;
    public Label lblHoraInicio;
    public Label lblHoraFin;
    public Label lblCuposLibres;
    public Button btnAgregarHorario;
    public Label lblNombreCentro;

    private List<HorarioDTO> horarios = new ArrayList<>();

    private MyListenerHorario myListenerHorario;
    private HorarioDTO horarioSeleccionado;

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

    public void guardarReserva(ActionEvent actionEvent) throws IOException {
        listenerAct = new MyListenerAct() {
            @Override
            public void onClickListenerAct(SuperActividadDTO act) {
                //setear las labels e info con la info de act
                actividad = act;
                System.out.println("clickeaste "+ act.getNombreServicio());
                nombreAct.setText(act.getNombreServicio());
                direccionAct.setText(act.getAddress() +", " + act.getBarrio());
                precioAct.setText("Costo: $" + act.getPrecio());
                //hacer request mery nombre centro, actividad, horariodto
            }
        };

        //cargar vista reserva en ventana emergente, mismo controller y hacer coso horarios y boton reservar
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaController.class.getResourceAsStream("reservaActividad.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");

        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        horarios.addAll(actividad.getHorarios());
        int row=0;
        try {
            for (int i=0; i<horarios.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardHorarioActividad.fxml"));
                HBox horarioBox = fxmlLoader.load();

                setDatosHorario(horarios.get(i),myListenerHorario);

                contenedorHorarios.add(horarioBox,0,row++);
                //GridPane.setMargin(horarioBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setDatosHorario(HorarioDTO horario, MyListenerHorario myListenerHorario){
        this.horarioSeleccionado=horario;
        this.myListenerHorario=myListenerHorario;
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
        //pasar los horarios en int a horarios normales en string

        int horaInicio = horario.getHoraInicio();
        int hora = horaInicio/100;
        String horaInicioStr = String.valueOf(hora) + ":" + String.valueOf(horaInicio-hora*100);

        lblHoraInicio.setText(horaInicioStr);
        int horaFin = horario.getHoraFin();
        int horaF = horaFin/100;
        String horaFinStr = String.valueOf(horaF) + ":" + String.valueOf(horaFin-horaF*100);

        lblHoraFin.setText(horaFinStr);
        //falta lblCuposLibres.setText(horario.getCupos()); o hacer la cuenta de los libres si esos son los totales

    }

    public void clickHorario(MouseEvent mouseEvent) {
        myListenerHorario.onClickListenerHorario(horarioSeleccionado);
    }

    public void guardarReservaFinal(ActionEvent actionEvent) {
        btnAgregarHorario.setText("*");
        myListenerHorario = new MyListenerHorario() {
            @Override
            public void onClickListenerHorario(HorarioDTO horario) {
                System.out.println(horario.getDia() + horario.getHoraInicio() + horario.getHoraFin());
            }
        };
    }
}
