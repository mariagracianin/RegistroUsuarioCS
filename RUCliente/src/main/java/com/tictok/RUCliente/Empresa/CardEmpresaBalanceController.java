package com.tictok.RUCliente.Empresa;

import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
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

    private UsuarioResumenDTO esteUsuario;

    public void setEsteUsuario(UsuarioResumenDTO esteUsuario) {
        this.esteUsuario = esteUsuario;
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

    public void ampliar(ActionEvent actionEvent) {
    }
}
