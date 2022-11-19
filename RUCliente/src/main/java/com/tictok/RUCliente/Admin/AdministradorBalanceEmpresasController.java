package com.tictok.RUCliente.Admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.BalanceDTO;
import com.tictok.RUCliente.AdminRest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AdministradorBalanceEmpresasController implements Initializable {

    public ChoiceBox<String> choiceBoxMes;
    public ChoiceBox<String> choiceBoxAño;
    public Label valorImporteTotal;
    public TableView<BalanceDTO> tblEmpresasResumen;
    public TableColumn<TableView<BalanceDTO>,String> colEmpresa;
    public TableColumn<TableView<BalanceDTO>,String> colRUT;
    public TableColumn<TableView<BalanceDTO>,Double> colImporte;
    public TableColumn<TableView<BalanceDTO>,Integer> colCantCheckIns;
    public TableColumn<TableView<BalanceDTO>,Integer> colCantUsuarios;
    public TableColumn<TableView<BalanceDTO>,String> colEncargado;
    public TableColumn<TableView<BalanceDTO>,String> colDireccion;
    public TableColumn<TableView<BalanceDTO>,String> colTelefono;

    private ObservableList<BalanceDTO> filas;


    @Autowired
    AdministradorController administradorController;
    @Autowired
    AdminRest adminRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};

        choiceBoxMes.getItems().addAll(mes);
        choiceBoxAño.getItems().addAll("2020","2021","2022","2023");

        Calendar c = Calendar.getInstance();
        choiceBoxAño.setValue(c.get(Calendar.YEAR)+"");
        choiceBoxMes.setValue(mes[c.get(Calendar.MONTH)]);

        this.colEmpresa.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colRUT.setCellValueFactory(new PropertyValueFactory("rut"));
        this.colImporte.setCellValueFactory(new PropertyValueFactory("importe"));
        this.colCantCheckIns.setCellValueFactory(new PropertyValueFactory("cantidadDeCheckIns"));
        this.colCantUsuarios.setCellValueFactory(new PropertyValueFactory("cantidadUsuarios"));
        this.colEncargado.setCellValueFactory(new PropertyValueFactory("encargado"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory("address"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));


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
            HttpResponse<String> response = adminRest.obtenerBalanceEmpresas(intMes, Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<BalanceDTO> list = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, BalanceDTO.class));

            this.filas = FXCollections.observableList(list);
            this.tblEmpresasResumen.setItems(filas);

            Double importeTotal = (double) 0;
            for (int i = 0; i<this.filas.size(); i++){
                importeTotal += this.filas.get(i).getImporte();
            }

            valorImporteTotal.setText(importeTotal + "");

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void registrarEmpresa(ActionEvent actionEvent) throws IOException {
        administradorController.registrarEmpresa(actionEvent);
    }

    public void registrarCentro(ActionEvent actionEvent) throws IOException {
        administradorController.registrarCentro(actionEvent);
    }

    public void mostrarBalanceCentros(ActionEvent actionEvent) throws IOException {
        administradorController.mostrarBalanceCentros(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        administradorController.salir(actionEvent);
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
            HttpResponse<String> response = adminRest.obtenerBalanceEmpresas(intMes, Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<BalanceDTO> list = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, BalanceDTO.class));

            this.filas = FXCollections.observableList(list);
            this.tblEmpresasResumen.setItems(filas);

            Double importeTotal = (double) 0;
            for (int i = 0; i<this.filas.size(); i++){
                importeTotal += this.filas.get(i).getImporte();
            }

            valorImporteTotal.setText(importeTotal + "");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void cargaDeDatos(ActionEvent actionEvent) {
        administradorController.cargaDeDatos(actionEvent);
    }
}
