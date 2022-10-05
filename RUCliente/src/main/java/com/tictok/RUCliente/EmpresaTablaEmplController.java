package com.tictok.RUCliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class EmpresaTablaEmplController {

    @Autowired
    private UsuarioRest usuarioRest;

    public Button btnActualizar;
    public TableColumn<TableView<UsuarioDTO>,String> colCorreo;
    public TableColumn<TableView<UsuarioDTO>,String> colNombres;
    public TableColumn<TableView<UsuarioDTO>,String> colApellidos;
    public TableColumn<TableView<UsuarioDTO>,Integer> colCedula;
    public TableColumn<TableView<UsuarioDTO>, LocalDate> colVenCarne;
    public TableColumn<TableView<UsuarioDTO>,Double> colSaldo;
    public TableView<UsuarioDTO> tablEmpl;
    private ObservableList<UsuarioDTO> empleados;


    public void actualizar(ActionEvent actionEvent) throws JsonProcessingException {
        empleados = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("cuentaMail"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory("saldo"));

        try {
            HttpResponse<String> response = usuarioRest.obtenerUsuariosFromEmpresaX(null);
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<UsuarioDTO> listUsuariosDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, UsuarioDTO.class));

            ObservableList<UsuarioDTO> observableListUsuariosDTO = FXCollections.observableList(listUsuariosDTO);
            this.empleados = observableListUsuariosDTO;
            this.tablEmpl.setItems(empleados);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        //this.empleados.add(cada uno de la request sql)
    }
    public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        this.salir(actionEvent);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(EmpresaRegistroEmplController.class.getResourceAsStream("empresa_registro_empleado.fxml"));
        Stage stage = new Stage();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setTitle("Registrar nuevo empleado");

        stage.show(); //no es ventana emergente
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) {

        Node source = (Node)  actionEvent.getSource();
        Stage stageActual  = (Stage) source.getScene().getWindow();
        stageActual.close();
    }
}
