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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("mail"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory("saldo"));


        HttpResponse<JsonNode> response = usuarioRest.obtenerUsuariosFromEmpresaX(null);
        String responseBody = response.getBody().toString();
        System.out.println(responseBody);

        ObjectMapper mapper = new ObjectMapper();
        ObservableList<UsuarioDTO> listUsuariosDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, UsuarioDTO.class));

        this.empleados = listUsuariosDTO;
        this.tablEmpl.setItems(empleados);
        //this.empleados.add(cada uno de la request sql)
    }
}
