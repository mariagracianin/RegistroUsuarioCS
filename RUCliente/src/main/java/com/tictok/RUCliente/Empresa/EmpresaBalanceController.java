package com.tictok.RUCliente.Empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.Commons.ServicioResumenDTO;
import com.tictok.RUCliente.Empleado.CardCanchaController;
import com.tictok.RUCliente.EmpresaRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpresaBalanceController implements Initializable {

    public VBox contenedor;
    public Label valorImporteTotal;
    public ChoiceBox<String> choiceBoxAño;
    public ChoiceBox<String> choiceBoxMes;
    private List<UsuarioResumenDTO> lista;

    @Autowired
    EmpresaController empresaController;
    @Autowired
    EmpresaRest empresaRest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"};

        choiceBoxMes.getItems().addAll(mes);
        choiceBoxAño.getItems().addAll("2020","2021","2022","2023");

        Calendar c = Calendar.getInstance();
        choiceBoxAño.setValue(c.get(Calendar.YEAR)+"");
        choiceBoxMes.setValue(mes[c.get(Calendar.MONTH)]);

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

        contenedor.getChildren().clear();

        try{
            HttpResponse<String> response = empresaRest.obtenerBalanceGeneral(intMes,Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            this.lista = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, UsuarioResumenDTO.class));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<lista.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empresa/cardEmpresaBalance.fxml"));
                HBox filaBox = fxmlLoader.load();

                CardEmpresaBalanceController cardController = fxmlLoader.getController();
                cardController.setData(lista.get(i).getCedula(), lista.get(i).getNombre(), lista.get(i).getApellido(), lista.get(i).getCantidadCheckIns(), lista.get(i).getImporte(), lista.get(i).getSaldo(), lista.get(i).getSaldoBase(), lista.get(i).getSobregiro());
                cardController.setEsteUsuario(lista.get(i));

                contenedor.getChildren().add(filaBox);
                contenedor.setSpacing(5);

                double importeTotal = 0;

                for (UsuarioResumenDTO usuarioResumenDTO : this.lista) {
                    importeTotal += usuarioResumenDTO.getImporte();
                }

                valorImporteTotal.setText(importeTotal + "");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        empresaController.registrarUsuario(actionEvent);
    }

    public void mostrarTablaEmpleados(ActionEvent actionEvent) throws IOException {
        empresaController.mostrarTablaEmpleados(actionEvent);
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        empresaController.agregarCuenta(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        empresaController.salir(actionEvent);
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

        contenedor.getChildren().clear();

        try{
            HttpResponse<String> response = empresaRest.obtenerBalanceGeneral(intMes,Integer.parseInt(choiceBoxAño.getValue()));
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            this.lista = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, UsuarioResumenDTO.class));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<lista.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empresa/cardEmpresaBalance.fxml"));
                HBox filaBox = fxmlLoader.load();

                CardEmpresaBalanceController cardController = fxmlLoader.getController();
                cardController.setData(lista.get(i).getCedula(), lista.get(i).getNombre(), lista.get(i).getApellido(), lista.get(i).getCantidadCheckIns(), lista.get(i).getImporte(), lista.get(i).getSaldo(), lista.get(i).getSaldoBase(), lista.get(i).getSobregiro());
                cardController.setEsteUsuario(lista.get(i));

                contenedor.getChildren().add(filaBox);
                contenedor.setSpacing(5);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
