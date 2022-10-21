package com.tictok.RUCliente.Empleado;

import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
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
public class EmpCanchasConFiltrosController implements Initializable {
    public ChoiceBox<String> filtroBarrio;
    public GridPane contenedorCanchas;
    private ObservableList<String> opcionesBarrios = FXCollections.observableArrayList("Pocitos","Centro","Ciudad Vieja","Tres Cruces","Malvín","Parque Rodó");

    @Autowired
    EmpCanchasController empCanchasController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filtroBarrio.setItems(opcionesBarrios);
    }

    public void verReservas(ActionEvent actionEvent) throws IOException {
        empCanchasController.verReservas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empCanchasController.cerrarSesion(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empCanchasController.verReservasPasadas(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empCanchasController.verDatos(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empCanchasController.verActividades(actionEvent);
    }

    public void ventanaIntroducirCodigoPadre(ActionEvent actionEvent) throws IOException {
        empCanchasController.ventanaIntroducirCodigoPadre(actionEvent);
    }

    public void aplicarFiltros(ActionEvent actionEvent) {
        if (filtroBarrio.getValue() !=null){
            aplicarFiltroBarrio(filtroBarrio.getValue());
        }else{
            System.out.println("barrio es null");
        }
    }
    private void aplicarFiltroBarrio(String barrio){
        List<SuperCanchaDTO> canchasActuales= new ArrayList<>();
        contenedorCanchas.getChildren().clear();
       //canchasActuales = lista de la BD donde barrio = barrio;
        int column=0;
        int row=0;
        try {
            for (int i=0; i<canchasActuales.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("/com/tictok/RUCliente/Empleado/cardCancha.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardCanchaController cardController = fxmlLoader.getController();
                cardController.setDatosCancha(canchasActuales.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                contenedorCanchas.add(actBox,column++,row);
                GridPane.setMargin(actBox, new Insets(10));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
