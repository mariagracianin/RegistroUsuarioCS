package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.JavaFXApplication;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.camel.reifier.ResequenceReifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpMisReservasController implements Initializable {

    @Autowired
    UsuarioRest usuarioRest;

    public GridPane contenedorReservas;
    private  List<ReservaDTO> reservas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reservas= new ArrayList<>();
        contenedorReservas.getChildren().clear();
        try {
            reservas.addAll(getDatos());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int column=0;
        int row=0;

        try {
            for (int i=0; i<reservas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardReserva.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardReservaRealizadaController cardController = fxmlLoader.getController();
                cardController.setDatos(reservas.get(i));
               // cardReservaRealizadaController.setDatos(reservas.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                contenedorReservas.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<ReservaDTO> getDatos() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        List<ReservaDTO> list = mapper.readValue(usuarioRest.buscarReservasFromUsuarioLogeado().getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, ReservaDTO.class));

        return list ;
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpMisReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empActividades.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Actividades");

        stage.show(); //no es ventana emergente
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
        stageActual.setTitle("Login");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
        stageActual.setScene(escena);
        stageActual.show();
    }

    public void verReservasPasadas(ActionEvent actionEvent) {

    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpMisReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empMisDatos.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Mis Datos");

        stage.show(); //no es ventana emergente
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpMisReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empCanchas.fxml"));
        Stage stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Canchas");

        stage.show(); //no es ventana emergente
    }
}
