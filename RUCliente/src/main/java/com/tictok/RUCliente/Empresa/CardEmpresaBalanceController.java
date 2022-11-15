package com.tictok.RUCliente.Empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CheckInDTO;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.RUCliente.Centro.CentroController;
import com.tictok.RUCliente.EmpresaRest;
import com.tictok.RUCliente.Main;
import com.tictok.RUCliente.MiniCuenta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardEmpresaBalanceController implements Initializable {
    public Label lblCedula;
    public Label lblNombre;
    public Label lblApellido;
    public Label lblCantCheckIns;
    public Label lblImporte;
    public Label lblSaldo;
    public Label lblSaldoBase;
    public Label lblSobregiro;
    private MiniCuenta miniCuenta;
    private UsuarioResumenDTO esteUsuario;
    private int esteMes;
    private int esteAño;

    public void setEsteUsuario(UsuarioResumenDTO esteUsuario) {
        this.esteUsuario = esteUsuario;
    }

    public void setMiniCuenta(MiniCuenta miniCuenta) {
        this.miniCuenta = miniCuenta;
    }

    public void setEsteMes(int esteMes) {
        this.esteMes = esteMes;
    }

    public void setEsteAño(int esteAño) {
        this.esteAño = esteAño;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setData(int cedula, String nombre, String apellido, int checkIn, double importe, double saldo, double saldoBase, double sobregiro ){
        lblCedula.setText(""+ cedula);
        lblNombre.setText(nombre);
        lblApellido.setText(apellido);
        lblCantCheckIns.setText(checkIn+"");
        lblImporte.setText(importe +"");
        lblSaldo.setText(saldo +"");
        lblSaldoBase.setText(saldoBase +"");
        lblSobregiro.setText(sobregiro+"");
    }

    public void ampliar(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(EmpresaBalanceController.class.getResourceAsStream("/com/tictok/RUCliente/Empresa/empresaBalanceAmpliar.fxml"));
        stage.setTitle("Balance ampliado de usuario");
        Scene escena = new Scene(root);
        escena.getStylesheets().add("/com/tictok/RUCliente/entidad_style.css");
        stage.setScene(escena);
        stage.show();

        EmpresaBalanceAmpliarController controller =  fxmlLoader.getController();
        controller.setEsteUsuario(esteUsuario);
        controller.setEsteAño(esteAño);
        controller.setEsteMes(esteMes);
        controller.cargarCheckIns();



    }
}
