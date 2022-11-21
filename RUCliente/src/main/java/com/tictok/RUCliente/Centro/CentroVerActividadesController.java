package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CentroVerActividadesController implements Initializable {

    public TableColumn<TableView<SuperActividadDTO>, String> colNombre;
    public TableColumn<TableView<SuperActividadDTO>,Double> colPrecio;
    public TableView<SuperActividadDTO> tblAct;
    private ObservableList<SuperActividadDTO> actividades;

    @Autowired
    CentroController centroController;
    @Autowired
    CentroDeportivoRest centroDeportivoRest;
    @Autowired
    MiniCuenta miniCuenta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));

        try {
            HttpResponse<String> response = CentroDeportivoRest.obtenerActividadesFromCentroLogeado(this.miniCuenta);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<SuperActividadDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperActividadDTO.class));
            this.actividades = FXCollections.observableList(listSuperActividadesDTO);
            this.tblAct.setItems(actividades);
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

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        centroController.verCanchas(actionEvent);
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
