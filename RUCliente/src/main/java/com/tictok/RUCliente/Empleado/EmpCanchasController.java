package com.tictok.RUCliente.Empleado;
//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.*;
import com.tictok.RUCliente.Empresa.EmpresaRegistroEmplController;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.CentroDeportivoRest;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    public Pagination pagination;
    public BorderPane pane;
    @Autowired
    EmpMisDatosController empMisDatosController;
    @Autowired
    EmpMisReservasController empMisReservasController;
    @Autowired
    MiniCuenta miniCuenta;
    @Autowired
    CentroDeportivoRest centroDeportivoRest;
    @Autowired
    UsuarioRest usuarioRest;
    @FXML
    private GridPane contenedorCanchas;
    private List<SuperCanchaDTO> canchasActuales;
    /* private List<SuperCanchaDTO> getDatos(){
         try {
             HttpResponse<String> response = centroDeportivoRest.obtenerCanchas();
             String responseBody = response.getBody();
             ObjectMapper mapper = new ObjectMapper();
             List<SuperCanchaDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));
             return listSuperActividadesDTO;
         }catch (Exception e){
             throw new RuntimeException(e);
         }
     }*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL linkLupa = getClass().getResource("/com/tictok/RUCliente/Empleado/lupa.png");
        Image imagenLupa = new Image(linkLupa.toString(), 25, 25, false, true);
        btnBuscar.setGraphic(new ImageView(imagenLupa));
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.setMaxHeight(550);
        pagination.setMaxWidth(900);
        // pagination.setPageFactory(this::createPage);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                pane.setPrefHeight(650);
                pane.setPrefWidth(1200);
                pane.getScene().getWindow().setWidth(pane.getScene().getWidth() + 0.001);
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
    public void hacerDespues(){
        pagination.getScene().getWindow().setWidth(pagination.getScene().getWidth() + 0.001);
        pane.getScene().getWindow().setWidth(pane.getScene().getWidth() + 0.001);
    }
    private GridPane createPage(Integer pageIndex) {
        GridPane contenedorCan = new GridPane();
        contenedorCan.setPrefWidth(800);
        contenedorCan.setPrefHeight(450);
        contenedorCan.setMaxWidth(900);
        contenedorCan.setMaxHeight(550);
        contenedorCan.getColumnConstraints().clear();
        contenedorCan.getRowConstraints().clear();
        ListaCanchasDTOConCount listaCanchasDTOConCount;
        try {
            HttpResponse<String> response = usuarioRest.obtenerCanchasPageable(pageIndex, 9);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            listaCanchasDTOConCount = mapper.readValue(responseBody, ListaCanchasDTOConCount.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        int column=0;
        int row=0;
        pagination.setPageCount(listaCanchasDTOConCount.getPages());
        try {
            for (int i = 0; i< listaCanchasDTOConCount.getObjects().size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
                SplitPane actBox = fxmlLoader.load();
                CardCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosCancha(listaCanchasDTOConCount.getObjects().get(i));
                cardController.setMiniCuenta(miniCuenta);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorCan.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getScene().getWindow().setWidth(pane.getScene().getWidth() + 0.001);
        return contenedorCan;
    }
    public void verReservas(ActionEvent actionEvent) throws IOException {
        empMisDatosController.verReservas(actionEvent);
    }
    public void verActividades(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verActividades(actionEvent);
    }
    public void verDatos(ActionEvent actionEvent) throws IOException {
        empMisReservasController.verDatos(actionEvent);
    }
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empMisReservasController.cerrarSesion(actionEvent);
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

    private GridPane createPageBuscador(Integer pageIndex) {
        System.out.println("Page Indeeeeeeeeeeeeeeeeeeeeeeeex  "+ pageIndex);
        if (txtBuscador.getText().isEmpty()){
            GridPane contenedorCan = new GridPane();
            contenedorCan.setPrefWidth(900);
            contenedorCan.setPrefHeight(550);
            contenedorCan.setMaxWidth(900);
            contenedorCan.setMaxHeight(550);
            contenedorCan.getColumnConstraints().clear();
            contenedorCan.getRowConstraints().clear();
            ListaCanchasDTOConCount listaCanchasDTOConCount;
            try {
                HttpResponse<String> response = usuarioRest.obtenerCanchasPageable(pageIndex, 9);
                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                listaCanchasDTOConCount = mapper.readValue(responseBody, ListaCanchasDTOConCount.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int column = 0;
            int row = 0;
            pagination.setPageCount(listaCanchasDTOConCount.getPages());
            try {
                for (int i = 0; i < listaCanchasDTOConCount.getObjects().size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
                    SplitPane actBox = fxmlLoader.load();
                    CardCanchaController cardController = fxmlLoader.getController();
                    cardController.setDatosCancha(listaCanchasDTOConCount.getObjects().get(i));
                    cardController.setMiniCuenta(miniCuenta);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    contenedorCan.add(actBox, column++, row);
                    GridPane.setMargin(actBox, new Insets(5));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contenedorCan;
        }else {
            GridPane contenedorCan = new GridPane();
            contenedorCan.setPrefWidth(900);
            contenedorCan.setPrefHeight(550);
            contenedorCan.setMaxWidth(900);
            contenedorCan.setMaxHeight(550);
            contenedorCan.getColumnConstraints().clear();
            contenedorCan.getRowConstraints().clear();
            ListaCanchasDTOConCount listaCanchasDTOConCount;
            try {
                HttpResponse<String> response = usuarioRest.obtenerCanchasByFiltroPageable(txtBuscador.getText(), pageIndex, 9);
                String responseBody = response.getBody();
                ObjectMapper mapper = new ObjectMapper();
                listaCanchasDTOConCount = mapper.readValue(responseBody, ListaCanchasDTOConCount.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int column = 0;
            int row = 0;
            pagination.setPageCount(listaCanchasDTOConCount.getPages());
            try {
                for (int i = 0; i < listaCanchasDTOConCount.getObjects().size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    // fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
                    SplitPane actBox = fxmlLoader.load();
                    CardCanchaController cardController = fxmlLoader.getController();
                    cardController.setDatosCancha(listaCanchasDTOConCount.getObjects().get(i));
                    cardController.setMiniCuenta(miniCuenta);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    contenedorCan.add(actBox, column++, row);
                    GridPane.setMargin(actBox, new Insets(5));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contenedorCan;
        }
    }
    public void  llamarBuscador(ActionEvent actionEvent) throws JsonProcessingException {
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                pane.setPrefHeight(750);
                pane.setPrefWidth(1200);
                return createPageBuscador(pageIndex);
            }
        });
//        /*
//        contenedorCanchas.getChildren().clear();
//        System.out.println("lo llamaaaaaaaaaaaaaaaaaaaa");
//        System.out.println(txtBuscador.getText());
//        HttpResponse<String> response =  centroDeportivoRest.obtenerCanchasByFiltro(txtBuscador.getText());
//        System.out.println(response.getBody());
//        ObjectMapper mapper = new ObjectMapper();
//        List<SuperCanchaDTO> listSuperDTO = mapper.readValue(response.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));
//        int column=0;
//        int row=0;
//        try {
//            for (int i=0; i<listSuperDTO.size(); i++){
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
//                SplitPane actBox = fxmlLoader.load();
//                CardCanchaController cardController = fxmlLoader.getController();
//                cardController.setDatosCancha(listSuperDTO.get(i));
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//                contenedorCanchas.add(actBox,column++,row);
//                GridPane.setMargin(actBox, new Insets(10));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } */
    }
}
