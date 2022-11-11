package com.tictok.RUCliente.Centro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CentroCheckInUsuarioSinReservaController{

    public GridPane contenedorActividades;
    public GridPane contenedorCanchas;
    private int cedulaUsuario;

    @Autowired
    MiniCuenta miniCuenta;

    @Autowired
    CentroController centroController;


    public void inicializar(){
        ArrayList<SuperActividadDTO> actividades = getActividadesDeCentro();

        int row=0;

        try {
            for (int i=0; i<actividades.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                System.out.println("-----------------------------------------wtf");
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Centro/cardCheckInSinReserva.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCheckInSinReservaController  cardController = fxmlLoader.getController();
                cardController.setDatos(actividades.get(i), null, this.cedulaUsuario);
                cardController.setMiniCuenta(this.miniCuenta);
                cardController.setCedula(this.cedulaUsuario);
                System.out.println("CEDULA EN TXT2: " +this.cedulaUsuario+ "-----------------------------");

                contenedorActividades.add(actBox,0,row++);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<SuperCanchaDTO> canchas = getCanchasDeCentro();
        row=0;

        try {
            for (int i=0; i<canchas.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Centro/cardCheckInSinReserva.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCheckInSinReservaController  cardController = fxmlLoader.getController();
                cardController.setDatos(null, canchas.get(i), this.cedulaUsuario);
                cardController.setMiniCuenta(this.miniCuenta);

                contenedorCanchas.add(actBox,0,row++);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<SuperCanchaDTO> getCanchasDeCentro() {
        try {
            HttpResponse<String> response = CentroDeportivoRest.obtenerCanchasFromCentroLogeado(this.miniCuenta);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<SuperCanchaDTO> listSuperCanchasDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));

            return listSuperCanchasDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private ArrayList<SuperActividadDTO> getActividadesDeCentro(){
        try {
            HttpResponse<String> response = CentroDeportivoRest.obtenerActividadesFromCentroLogeado(this.miniCuenta);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<SuperActividadDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperActividadDTO.class));

            return listSuperActividadesDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void agregarAct(ActionEvent actionEvent) throws IOException {
        centroController.agregarAct(actionEvent);
    }

    public void agregarCancha(ActionEvent actionEvent) throws IOException {
        centroController.agregarCancha(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) {
        centroController.verActividades(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) {
        centroController.verCanchas(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        centroController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
        centroController.salir(actionEvent);
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
}
