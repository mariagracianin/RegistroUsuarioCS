package com.tictok.RUCliente.Empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUCliente.EmpresaRest;
import com.tictok.RUCliente.MiniCuenta;
import com.tictok.RUCliente.UsuarioRest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpresaTablaEmplController implements Initializable {

    @Autowired
    MiniCuenta miniCuenta;
    @Autowired
    private EmpresaRest empresaRest;
    @Autowired
    EmpresaController empresaController;

    public Button btnActualizar;
    public TableColumn<TableView<UsuarioDTO>,String> colCorreo;
    public TableColumn<TableView<UsuarioDTO>,String> colNombres;
    public TableColumn<TableView<UsuarioDTO>,String> colApellidos;
    public TableColumn<TableView<UsuarioDTO>,Integer> colCedula;
    public TableColumn<TableView<UsuarioDTO>, LocalDate> colVenCarne;
    public TableColumn<TableView<UsuarioDTO>,Double> colSaldo;
    public TableView<UsuarioDTO> tablEmpl;
    private ObservableList<UsuarioDTO> empleados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.refrescar();
    }
    private void refrescar(){
        this.colNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("cuentaMail"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory("saldo"));

        try {
            HttpResponse<String> response = empresaRest.obtenerUsuariosFromEmpresaLogeada();
            String responseBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<UsuarioDTO> listUsuariosDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, UsuarioDTO.class));

            this.empleados = FXCollections.observableList(listUsuariosDTO);
            this.tablEmpl.setItems(empleados);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void actualizar(ActionEvent actionEvent) throws JsonProcessingException {
        this.refrescar();
    }
    public void registrarUsuario(ActionEvent actionEvent) throws IOException {
        empresaController.registrarUsuario(actionEvent);
    }

    public void mostrarLiquidacion(ActionEvent actionEvent) throws IOException {
        empresaController.mostrarLiquidacion(actionEvent);
    }

    public void salir(ActionEvent actionEvent) throws IOException {
    empresaController.salir(actionEvent);
    }

    public void agregarCuenta(ActionEvent actionEvent) throws IOException {
        empresaController.agregarCuenta(actionEvent);
    }
}
