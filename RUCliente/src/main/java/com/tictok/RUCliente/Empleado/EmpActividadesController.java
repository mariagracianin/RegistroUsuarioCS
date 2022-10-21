package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
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
        actividadesActuales= new ArrayList<>();
        contenedorAct.getChildren().clear();
        actividadesActuales.addAll(getDatos());
        int column=0;
        int row=0;

        try {
            for (int i=0; i<actividadesActuales.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                SplitPane actBox = fxmlLoader.load(EmpActividadesController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));

                CardActividadController cardController = fxmlLoader.getController();
                cardController.setDatosActividad(actividadesActuales.get(i));
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

}
