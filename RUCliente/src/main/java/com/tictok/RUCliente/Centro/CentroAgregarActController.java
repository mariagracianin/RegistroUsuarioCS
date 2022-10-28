package com.tictok.RUCliente.Centro;

import com.tictok.Commons.HorarioDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Empleado.CardHorarioActividadController;
import com.tictok.RUCliente.Empleado.VerHorariosActividadController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class CentroAgregarActController implements Initializable {
    public TextField txtPrecio;
    public TextField txtCupos;
    public TextField txtNombre;
    public RadioButton paseLibre;
    public Button btnGuardar;
    public Button btnSubirImg;
    public ChoiceBox diaOpciones;
    public TextField txtHoraInicio;
    public TextField txtHoraFin;
    public Button btnAgregarHorario;
    public GridPane contenedorHorarios;
    public AnchorPane anchorPane;
    public Label lblSubirImgOk;
    private String img;
    private int row =0;

    private ArrayList<HorarioDTO> horariosReserva;

    @Autowired
    CentroController centroController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        horariosReserva = new ArrayList<>();
        diaOpciones.setItems(FXCollections.observableArrayList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        paseLibre.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(paseLibre.isSelected()){
                    txtCupos.setDisable(true);
                }
            }
        });
    }


    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void guardarDatos(ActionEvent actionEvent){
        if (txtCupos.isDisable()){
            //centroDeportivoRest.guardarActividad(txtNombre.getText(),float)
            //mandar datos con pase libre = true
            //sin horarios

        }else{
            //mandar datos con pase libre = false y lista de horarios
        }
    }

    public void agregarHorario(ActionEvent actionEvent) throws IOException {
        String hInicio = txtHoraInicio.getText().substring(0,2) + txtHoraInicio.getText().substring(3);
        int horaInicio = Integer.parseInt(hInicio);

        String hFin = txtHoraFin.getText().substring(0,2) + txtHoraFin.getText().substring(3);
        int horaFin = Integer.parseInt(hFin);
        int dia;
        if (diaOpciones.getValue().equals("Lunes")){
            dia = 1;
        }else if(diaOpciones.getValue().equals("Martes")){
            dia = 2;
        }else if (diaOpciones.getValue().equals("Miércoles")){
            dia = 3;
        }else if (diaOpciones.getValue().equals("Jueves")){
            dia = 4;
        }else if (diaOpciones.getValue().equals("Viernes")){
            dia = 5;
        }else if (diaOpciones.getValue().equals("Sábado")){
            dia = 6;
        }else{
            dia = 7;
        }

        HorarioDTO horarioNuevo = new HorarioDTO(dia, horaInicio, horaFin);
        if (!horariosReserva.contains(horarioNuevo)) {
            horariosReserva.add(horarioNuevo);
            actualizarHorariosReserva();
        }
    }
    public void eliminarHorario(HorarioDTO horario) throws IOException {
        this.horariosReserva.remove(horario);
        actualizarHorariosReserva();

    }
    public void actualizarHorariosReserva() throws IOException {
        contenedorHorarios.getChildren().clear();
        for (int i = 0; i < this.horariosReserva.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            HBox horarioBox = fxmlLoader.load(CardHorarioController.class.getResourceAsStream("/com/tictok/RUCliente/Centro/cardHorario.fxml"));

            CardHorarioController cardHorarioController = fxmlLoader.getController();
            cardHorarioController.setLabels(this.horariosReserva.get(i));

            contenedorHorarios.add(horarioBox, 0, i + 1);
        }
    }

    public void abrirBuscadorArchivo(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("IMG files", "*.jpg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(selectedFile));
                String img = new String(encoded, StandardCharsets.US_ASCII);
                this.img = img;
                lblSubirImgOk.setText("Imagen subida exitosamente");
                //System.out.println(img);

            } else {
                lblSubirImgOk.setText("Error: debe seleccionar una imagen");
            }
        }catch (IOException e){
            lblSubirImgOk.setText("Error: el archivo subido no es válido, inténtelo de nuevo");
        }


    }
}
