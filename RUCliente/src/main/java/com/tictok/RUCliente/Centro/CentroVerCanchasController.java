package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CentroVerCanchasController implements Initializable {

    public TableColumn<TableView<SuperCanchaDTO>, String> colNombre;
    public TableColumn<TableView<SuperCanchaDTO>,Double> colPrecio;
    public TableView<SuperCanchaDTO> tblCan;
    private ObservableList<SuperCanchaDTO> canchas;

    @Autowired
    CentroController centroController;
    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerCanchas();
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<SuperCanchaDTO> listSuperCanchasDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));
            this.canchas = FXCollections.observableList(listSuperCanchasDTO);
            this.tblCan.setItems(canchas);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void irACheckIn(ActionEvent actionEvent) throws IOException {
        centroController.irACheckIn(actionEvent);
    }

    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        centroController.verActividades(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        centroController.agregarCuenta(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }
}
