package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.ListaActividadesDTOConCount;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EmpActividadesController implements Initializable {
    @FXML
    public BorderPane pane;
    public Button btnBuscar;
    public TextField txtBuscador;
    public Pagination pagination;

    @Autowired
    EmpMisDatosController empleadoMisDatosController;
    @Autowired
    EmpMisReservasController empMisReservasController;
    @Autowired
    VerHorariosActividadController verHorariosActividadController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @Autowired
    MiniCuenta miniCuenta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImagenesBotones();
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.setMaxHeight(550);
        pagination.setMaxWidth(900);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                pane.setPrefHeight(650);
                pane.setPrefWidth(1200);
                return createPage(pageIndex);
            }
        });
        pane.setPrefHeight(650);
        pane.setPrefWidth(1200);
        pagination.setMaxHeight(550);
        pagination.setMaxWidth(900);
        pagination.setPrefHeight(550);
        pagination.setPrefWidth(900);

    }

    private GridPane createPage(Integer pageIndex) {
        System.out.println(pageIndex);
        GridPane contenedorAct = new GridPane();
        contenedorAct.setPrefWidth(900);
        contenedorAct.setPrefHeight(550);
        contenedorAct.setMaxWidth(900);
        contenedorAct.setMaxHeight(550);
        contenedorAct.getColumnConstraints().clear();
        contenedorAct.getRowConstraints().clear();
        ListaActividadesDTOConCount listaActividadesDTOConCount;
        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerActividadesPageable(pageIndex, 9);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            listaActividadesDTOConCount = mapper.readValue(responseBody, ListaActividadesDTOConCount.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        int column=0;
        int row=0;

        pagination.setPageCount(listaActividadesDTOConCount.getPages());

        try {
            for (int i = 0; i< listaActividadesDTOConCount.getObjects().size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardActividadController cardController = fxmlLoader.getController();
                cardController.setDatosActividad(listaActividadesDTOConCount.getObjects().get(i));
                cardController.setMiniCuenta(miniCuenta);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorAct.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(5));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenedorAct;

    }

    private void setImagenesBotones() {
            URL linkLupa = getClass().getResource("/com/tictok/RUCliente/Empleado/lupa.png");
            Image imagenLupa = new Image(linkLupa.toString(),25,25,false,true);
            btnBuscar.setGraphic(new ImageView(imagenLupa));

    }


    public void verReservas(ActionEvent actionEvent) throws IOException {
        empleadoMisDatosController.verReservas(actionEvent);
    }

    public void verCanchas(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verCanchas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verDatos(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empMisReservasController.cerrarSesion(actionEvent);
    }

    private GridPane createPageBuscador(Integer pageIndex) {
        if (txtBuscador.getText().isEmpty()){
            GridPane contenedorAct = new GridPane();
            contenedorAct.setPrefWidth(900);
            contenedorAct.setPrefHeight(550);
            contenedorAct.setMaxWidth(900);
            contenedorAct.setMaxHeight(550);
            contenedorAct.getColumnConstraints().clear();
            contenedorAct.getRowConstraints().clear();
            ListaActividadesDTOConCount listaActividadesDTOConCount;
            try {
                HttpResponse<String> response = centroDeportivoRest.obtenerActividadesPageable(pageIndex, 9);
                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                listaActividadesDTOConCount = mapper.readValue(responseBody, ListaActividadesDTOConCount.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            int column = 0;
            int row = 0;

            pagination.setPageCount(listaActividadesDTOConCount.getPages());

            try {
                for (int i = 0; i < listaActividadesDTOConCount.getObjects().size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));
                    SplitPane actBox = fxmlLoader.load();

                    CardActividadController cardController = fxmlLoader.getController();
                    cardController.setDatosActividad(listaActividadesDTOConCount.getObjects().get(i));
                    cardController.setMiniCuenta(miniCuenta);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    contenedorAct.add(actBox, column++, row);
                    GridPane.setMargin(actBox, new Insets(5));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return contenedorAct;
        }else {

            GridPane contenedorAct = new GridPane();
            contenedorAct.setPrefWidth(900);
            contenedorAct.setPrefHeight(550);
            contenedorAct.setMaxWidth(900);
            contenedorAct.setMaxHeight(550);
            contenedorAct.getColumnConstraints().clear();
            contenedorAct.getRowConstraints().clear();
            ListaActividadesDTOConCount listaActividadesDTOConCount;
            try {
                HttpResponse<String> response = centroDeportivoRest.obtenerActividadesByFiltroPageable(txtBuscador.getText(), pageIndex, 9);
                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                listaActividadesDTOConCount = mapper.readValue(responseBody, ListaActividadesDTOConCount.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            int column = 0;
            int row = 0;

            pagination.setPageCount(listaActividadesDTOConCount.getPages());

            try {
                for (int i = 0; i < listaActividadesDTOConCount.getObjects().size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));
                    SplitPane actBox = fxmlLoader.load();

                    CardActividadController cardController = fxmlLoader.getController();
                    cardController.setDatosActividad(listaActividadesDTOConCount.getObjects().get(i));
                    cardController.setMiniCuenta(miniCuenta);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    contenedorAct.add(actBox, column++, row);
                    GridPane.setMargin(actBox, new Insets(5));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return contenedorAct;
        }

    }

    public void llamarBuscador(ActionEvent actionEvent) throws JsonProcessingException {

        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                pane.setPrefHeight(650);
                pane.setPrefWidth(1200);
                return createPageBuscador(pageIndex);
            }
        });

        /*
        contenedorAct.getChildren().clear();
        System.out.println(txtBuscador.getText());
        HttpResponse<String> response =  centroDeportivoRest.obtenerActividadesByFiltro(txtBuscador.getText());
        System.out.println(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        List<SuperActividadDTO> listSuperDTO = mapper.readValue(response.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, SuperActividadDTO.class));

        int column=0;
        int row=0;
        try {
            for (int i=0; i<listSuperDTO.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardActividad.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardActividadController cardController = fxmlLoader.getController();
                cardController.setDatosActividad(listSuperDTO.get(i));
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

        */
    }


}
