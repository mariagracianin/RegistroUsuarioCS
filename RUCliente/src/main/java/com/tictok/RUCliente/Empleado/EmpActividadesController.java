package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpActividadesController implements Initializable {
    @FXML
    public BorderPane pane;
    public Button btnBuscar;
    public TextField txtBuscador;
    @Autowired
    EmpleadoController empleadoController;
    @Autowired
    ReservarActividadController reservarActividadController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @FXML
    private GridPane contenedorAct;
    private List<SuperActividadDTO> actividadesActuales;


    private List<SuperActividadDTO> getDatos(){
        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerActividades();
            String responseBody = response.getBody();
            System.out.println("----------------------------------------------------------------");
            System.out.println(responseBody);
            ObjectMapper mapper = new ObjectMapper();
            List<SuperActividadDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperActividadDTO.class));

            return listSuperActividadesDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImagenesBotones();
        actividadesActuales= new ArrayList<>();
        contenedorAct.getChildren().clear();
        actividadesActuales.addAll(getDatos());
        int column=0;
        int row=0;

        try {
            for (int i=0; i<actividadesActuales.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
               // fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                SplitPane actBox = fxmlLoader.load(EmpActividadesController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));

                CardActividadController cardController = fxmlLoader.getController();
                cardController.setDatosActividad(actividadesActuales.get(i));
                System.out.println(actividadesActuales.get(i).getNombreServicio()+ "HOLA");

                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorAct.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setImagenesBotones() {
            URL linkLupa = getClass().getResource("/com/tictok/RUCliente/Empleado/lupa.png");
      /*  URL linkReservas = getClass().getResource("/com/tictok/RUCliente/Empleado/reserva.png");
        URL linkActividades = getClass().getResource("/com/tictok/RUCliente/Empleado/actividad.png");
        URL linkCancha = getClass().getResource("/com/tictok/RUCliente/Empleado/cancha.png");
        URL linkMisDatos = getClass().getResource("/com/tictok/RUCliente/Empleado/datos.png");
*/
            Image imagenLupa = new Image(linkLupa.toString(),25,25,false,true);
       /* Image imgReserva = new Image(linkReservas.toString(),30,30,false,true);
        Image imgAct = new Image(linkActividades.toString(),30,30,false,true);
        Image imgCan = new Image(linkCancha.toString(),30,30,false,true);
        Image imgDatos = new Image(linkMisDatos.toString(),30,30,false,true);

        btnMisReservas.setGraphic(new ImageView(imgReserva));
        btnCanchas.setGraphic(new ImageView(imgCan));
        btnActividades.setGraphic(new ImageView(imgAct));
        btnDatos.setGraphic(new ImageView(imgDatos));

        */
            btnBuscar.setGraphic(new ImageView(imagenLupa));


    }


    public void verReservas(ActionEvent actionEvent) throws IOException {
        empleadoController.verReservas(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empleadoController.verCanchas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empleadoController.verDatos(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }

    public void llamarBuscador(ActionEvent actionEvent) throws JsonProcessingException {
        contenedorAct.getChildren().clear();
        System.out.println(txtBuscador.getText());
        HttpResponse<String> response =  centroDeportivoRest.obtenerActividadesByFiltro(txtBuscador.getText());
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        List<SuperActividadDTO> listSuperDTO = mapper.readValue(response.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, SuperActividadDTO.class));

        int column=0;
        int row=0;
        try {
            for (int i=0; i<listSuperDTO.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardActividadController cardController = fxmlLoader.getController();
                cardController.setDatosActividad(listSuperDTO.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorAct.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
