package com.tictok.RUCliente;

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
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class EmpresaTablaEmplController {

    public Button btnActualizar;
    public TableColumn<TableView<UsuarioDTO>,String> colCorreo;
    public TableColumn<TableView<UsuarioDTO>,String> colNombres;
    public TableColumn<TableView<UsuarioDTO>,String> colApellidos;
    public TableColumn<TableView<UsuarioDTO>,Integer> colCedula;
    public TableColumn<TableView<UsuarioDTO>, LocalDate> colVenCarne;
    public TableColumn<TableView<UsuarioDTO>,Double> colSaldo;
    public TableView<UsuarioDTO> tablEmpl;
    private ObservableList<UsuarioDTO> empleados;


    public void actualizar(ActionEvent actionEvent) {
        empleados = FXCollections.observableArrayList();
        this.colNombres.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory("mail"));
        this.colVenCarne.setCellValueFactory(new PropertyValueFactory("vencimientoCarne"));
        this.colSaldo.setCellValueFactory(new PropertyValueFactory("saldo"));


        UsuarioDTO prueba = new UsuarioDTO("hola@ex.com","holahola",54967202,LocalDate.now(),"María","del Campo","093498330",10,5,10);
        this.empleados.add(prueba);
        this.tablEmpl.setItems(empleados);
        //this.empleados.add(cada uno de la request sql)
    }
}
