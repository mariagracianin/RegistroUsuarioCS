package com.tictok.RUCliente.Empleado;

//import com.tictok.Commons.HorarioConCuposDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.HttpResponse;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUCliente.CentroDeportivoRest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
public class EmpCanchasController implements Initializable {
    @Autowired
    EmpleadoController empleadoController;

    @Autowired
    CentroDeportivoRest centroDeportivoRest;

    @FXML
    private GridPane contenedorCanchas;
    private List<SuperCanchaDTO> canchasActuales= new ArrayList<>();

    private List<SuperCanchaDTO> getDatos(){
        try {
            HttpResponse<String> response = centroDeportivoRest.obtenerCanchas();
            String responseBody = response.getBody();
            System.out.println("----------------------------------------------------------------");
            System.out.println(responseBody);
            ObjectMapper mapper = new ObjectMapper();
            List<SuperCanchaDTO> listSuperActividadesDTO = mapper.readValue(responseBody, TypeFactory.defaultInstance().constructCollectionType(List.class, SuperCanchaDTO.class));
            for(int i = 0; i<listSuperActividadesDTO.size(); i++){
                SuperCanchaDTO actividadDTOI = listSuperActividadesDTO.get(i);
                System.out.println("CANCHA----------------");
                System.out.println("NOMBRE CENTRO: " + actividadDTOI.getNombreCentro());
                System.out.println("NOMBRE SERVICIO: " + actividadDTOI.getNombreServicio());
                System.out.println("ADRESS: " + actividadDTOI.getAddress());
                System.out.println("BARRIO: " + actividadDTOI.getBarrio());
                System.out.println("SUS HORARIOS---------------");
                System.out.println(actividadDTOI.getHorarios().size()+"horario/s--------------------------------------------------------------------------------------------------");
                for(int j=0; j<actividadDTOI.getHorarios().size(); j++){
                    System.out.println("DIA: " + actividadDTOI.getHorarios().get(j).getDia());
                    System.out.println("INICIO: " + actividadDTOI.getHorarios().get(j).getHoraInicio());
                    System.out.println("FIN: " + actividadDTOI.getHorarios().get(j).getHoraFin());
                }
            }
            return listSuperActividadesDTO;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        //aca mery me devolveria la lista de supercanchas q tengo q mostrar al usuario
        /*List<SuperCanchaDTO> canchas= new ArrayList<>();
        HorarioConCuposDTO h1 = new HorarioConCuposDTO(3,13,14,15);
        HorarioConCuposDTO h2 = new HorarioConCuposDTO(4,13,14,-1);
        HorarioConCuposDTO h3 = new HorarioConCuposDTO(3,13,14,10);
        ArrayList<HorarioConCuposDTO> h = new ArrayList<HorarioConCuposDTO>();
        h.add(h1);
        h.add(h2);
        h.add(h3);

        SuperCanchaDTO a1= new SuperCanchaDTO("Futbol", 500, "Club Bigua","direc","Pocitos","12345678",h, "/com/tictok/RUCliente/GETFITlogin.png");
        SuperCanchaDTO a2= new SuperCanchaDTO("Basquet", 400, "ACJ","direc","Centro","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");
        SuperCanchaDTO a3= new SuperCanchaDTO("Natacion", 500, "ACJ","direc","Punta Carretas","12345678",h,"/com/tictok/RUCliente/Empleado/imgactividadprueba.jpg");

        canchas.add(a1);
        canchas.add(a2);
        canchas.add(a3);
        canchas.add(a1);

        return canchas;*/
        //return null;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canchasActuales.addAll(getDatos());
        int column=0;
        int row=0;
        try {
            for (int i=0; i<canchasActuales.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardActividadOCancha.fxml"));
                SplitPane actBox = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
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

    public void verReservas(ActionEvent actionEvent) {
        empleadoController.verReservas(actionEvent);
    }

    public void verActividades(ActionEvent actionEvent) throws IOException {
        empleadoController.verActividades(actionEvent);
    }

    public void verDatos(ActionEvent actionEvent) throws IOException {
        empleadoController.verDatos(actionEvent);
    }

    public void verReservasPasadas(ActionEvent actionEvent) {
        empleadoController.verReservasPasadas(actionEvent);
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        empleadoController.cerrarSesion(actionEvent);
    }
}
