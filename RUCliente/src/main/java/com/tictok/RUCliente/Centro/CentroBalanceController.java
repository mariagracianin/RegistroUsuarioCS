package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.ServicioResumenDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.MiniCuenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CentroBalanceController implements Initializable {

    @Autowired
    MiniCuenta miniCuenta;
    @Autowired
    CentroDeportivoRest centroDeportivoRest;
    @Autowired
    CentroController centroController;

    public ChoiceBox<String> choiceBoxMes;
    public ChoiceBox<String> choiceBoxAño;
    public Button btnFiltrarMesAño;
    public TableView<ServicioResumenDTO> tblServicioResumen;
    public TableColumn<TableView<ServicioResumenDTO>,String> colServicio;
    public TableColumn<TableView<ServicioResumenDTO>,Integer> colCantCheckIns;
    public TableColumn<TableView<ServicioResumenDTO>,Double> colImporte;
    public Label valorImporteTotal;
    private ObservableList<ServicioResumenDTO> filas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};

        choiceBoxMes.getItems().addAll(mes);
        choiceBoxAño.getItems().addAll("2020","2021","2022","2023");

        Calendar c = Calendar.getInstance();
        choiceBoxAño.setValue(c.get(Calendar.YEAR)+"");
        choiceBoxMes.setValue(mes[c.get(Calendar.MONTH)]);

        this.colServicio.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        this.colCantCheckIns.setCellValueFactory(new PropertyValueFactory("cantidadCheckIns"));
        this.colImporte.setCellValueFactory(new PropertyValueFactory("importeTotal"));

        int intMes=0;
        switch (choiceBoxMes.getValue()){
            case "Enero": intMes =1;
                break;

            case "Febrero": intMes=2;
                break;

            case "Marzo": intMes=3;
                break;

            case "Abril": intMes=4;
                break;

            case "Mayo": intMes=5;
                break;

            case "Junio": intMes=6;
                break;

            case "Julio": intMes=7;
                break;

            case "Agosto": intMes=8;
                break;

            case "Setiembre": intMes=9;
                break;

            case "Octubre": intMes=10;
                break;

            case "Noviembre": intMes=11;
                break;

            case "Diciembre": intMes=12;
                break;

        }

        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerBalanceCentro(intMes, Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            System.out.println(responseBody + "response body");
            ObjectMapper mapper = new ObjectMapper();
            List<ServicioResumenDTO> list = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, ServicioResumenDTO.class));
            for (int i= 0; i<list.size(); i++){
                System.out.println(list.get(i).getNombreServicio());
                System.out.println(list.get(i).getCantidadCheckIns());

            }
            this.filas = FXCollections.observableList(list);
            this.tblServicioResumen.setItems(filas);

            Double importeTotal = (double) 0;
            for (int i = 0; i<this.filas.size(); i++){
                importeTotal += this.filas.get(i).getImporteTotal();
            }

            valorImporteTotal.setText(importeTotal + "");

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

    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void filtrarMesAño(ActionEvent actionEvent) {
        int intMes=0;

        switch (choiceBoxMes.getValue()){
            case "Enero": intMes =1;
                break;

            case "Febrero": intMes=2;
                break;

            case "Marzo": intMes=3;
                break;

            case "Abril": intMes=4;
                break;

            case "Mayo": intMes=5;
                break;

            case "Junio": intMes=6;
                break;

            case "Julio": intMes=7;
                break;

            case "Agosto": intMes=8;
                break;

            case "Setiembre": intMes=9;
                break;

            case "Octubre": intMes=10;
                break;

            case "Noviembre": intMes=11;
                break;

            case "Diciembre": intMes=12;
                break;

        }
        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerBalanceCentro(intMes, Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<ServicioResumenDTO> list = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, ServicioResumenDTO.class));

            this.filas = FXCollections.observableList(list);
            this.tblServicioResumen.setItems(filas);

            Double importeTotal = (double) 0;
            for (int i = 0; i<this.filas.size(); i++){
                importeTotal += this.filas.get(i).getImporteTotal();
            }

            valorImporteTotal.setText(importeTotal + "");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
