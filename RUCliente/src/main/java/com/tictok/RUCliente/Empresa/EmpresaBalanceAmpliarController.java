package com.tictok.RUCliente.Empresa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.CheckInDTO;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.RUCliente.EmpresaRest;
import com.tictok.RUCliente.MiniCuenta;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmpresaBalanceAmpliarController implements Initializable {
    public Label ciUsuario;
    public VBox contenedor;
    public Label valorImporteTotal;

    private UsuarioResumenDTO esteUsuario;
    private int esteMes;
    private int esteAño;

    @Autowired
    MiniCuenta miniCuenta;

    public void setEsteUsuario(UsuarioResumenDTO esteUsuario) {
        this.esteUsuario = esteUsuario;
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

    public void cargarCheckIns() {
        HttpResponse<String> response = EmpresaRest.obtenerBalanceDeUsuario(this.esteUsuario.getCedula(), esteMes, esteAño, miniCuenta);
        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<CheckInDTO> lista = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, CheckInDTO.class));

            for (int i = 0; i < lista.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empresa/cardEmpresaBalanceAmpliar.fxml"));
                HBox filaBox = fxmlLoader.load();
               // filaBox.setPadding(new Insets(0,0,0,5));

                CardEmpresaBalanceAmpliarController cardController = fxmlLoader.getController();
                cardController.setData(lista.get(i).getNombreActividad(), lista.get(i).getFecha(), lista.get(i).getPrecio().toString());

                contenedor.getChildren().add(filaBox);
                contenedor.setSpacing(5);
                GridPane.setMargin(filaBox, new Insets(5));

                double importeTotal = 0;

                for (CheckInDTO checkInDTO : lista) {
                    importeTotal += checkInDTO.getPrecio();
                }

                valorImporteTotal.setText(importeTotal + "");
                ciUsuario.setText(esteUsuario.getCedula() + "");

            }
            if (lista.isEmpty()) {
                valorImporteTotal.setText(0 + "");
                ciUsuario.setText(esteUsuario.getCedula() + "");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

