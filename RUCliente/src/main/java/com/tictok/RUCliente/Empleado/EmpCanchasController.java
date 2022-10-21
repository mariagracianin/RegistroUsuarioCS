package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpCanchasController implements Initializable {
    public Button btnBuscar;
    public Button btnUnirseAReserva;
    public Button btnCerrarSesion;
    public Button btnReservasPasadas;
    public Button btnDatos;
    public Button btnCanchas;
    public Button btnActividades;
    public Button btnMisReservas;
    public TextField txtBuscador;
    @Autowired
    EmpleadoController empleadoController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @FXML
    private GridPane contenedorCanchas;
    private List<SuperCanchaDTO> canchasActuales;

    //traer solo las que tienen cod reserva padre == null, o sea q no hayan sido reservadas x nadie;
    private List<SuperCanchaDTO> getDatos(){
        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerCanchas();
            String responseBody = response.getBody();
            System.out.println("----------------------------------------------------------------");
            System.out.println(responseBody);
            ObjectMapper mapper = new ObjectMapper();
            List<SuperCanchaDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));

            return listSuperActividadesDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImagenesBotones();
        canchasActuales= new ArrayList<>();
        contenedorCanchas.getChildren().clear();
        canchasActuales.addAll(getDatos());
        int column=0;
        int row=0;
        try {
            for (int i=0; i<canchasActuales.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosCancha(canchasActuales.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorCanchas.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void verReservas(ActionEvent actionEvent) throws IOException {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
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

    public void ventanaIntroducirCodigoPadre(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpCanchasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/ventEmergenteCodigoPadre.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.setTitle("Unirse a una reserva");
        escena.getStylesheets().add("/com/tictok/RUCliente/Empleado/actividad-cancha.css");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }
    private void setImagenesBotones(){
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
    public void llamarBuscador(ActionEvent actionEvent) {

    }
}
