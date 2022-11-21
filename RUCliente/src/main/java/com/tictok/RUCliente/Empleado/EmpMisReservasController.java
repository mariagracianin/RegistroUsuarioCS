package com.tictok.RUCliente.Empleado;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.tictok.Commons.HorarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Admin.AdministradorController;
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
import javafx.scene.image.Image;
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
        contenedorReservas.getChildren().clear();
        try {
            reservas = getDatos();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int column=0;
        int row=0;

        try {
            for (int i=0; i<reservas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
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
    public void actualizarReservas(){
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
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
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
        stage.getIcons().add(new Image(EmpMisReservasController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
        stage.setMaximized(true);
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
        stageActual.getIcons().add(new Image(EmpMisReservasController.class.getResourceAsStream("logo.png")));
        escena.getWindow().setWidth(900);
        escena.getWindow().setHeight(600);
        stageActual.show();
        stageActual.centerOnScreen();
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
        stage.getIcons().add(new Image(EmpMisReservasController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
        stage.setMaximized(true);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpMisReservasController.class.getResourceAsStream("/com/tictok/RUCliente/Empleado/empCanchas.fxml"));
        EmpCanchasController controller = fxmlLoader.getController();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Canchas");
        stage.getIcons().add(new Image(EmpMisReservasController.class.getResourceAsStream("logo.png")));
        stage.show(); //no es ventana emergente
        stage.centerOnScreen();
        stage.setMaximized(true);
        controller.hacerDespues();
        System.out.println("llega hasta acaaaaaaaaaaaaa");
    }
}
