package com.tictok.RUServidor.Services;

import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Repositories.ActividadRepository;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CargaDeDatosService {

    private final EmpresaService empresaService;
    private final UsuarioService usuarioService;
    private final CentroService centroService;
    private final ActividadService actividadService;
    private final CanchaService canchaService;
    private final CanchaRepository canchaRepository;
    private final ActividadRepository actividadRepository;

    public CargaDeDatosService(EmpresaService empresaService, UsuarioService usuarioService, CentroService centroService, ActividadService actividadService, CanchaService canchaService, CanchaRepository canchaRepository, ActividadRepository actividadRepository) {
        this.empresaService = empresaService;
        this.usuarioService = usuarioService;
        this.centroService = centroService;
        this.actividadService = actividadService;
        this.canchaService = canchaService;
        this.canchaRepository = canchaRepository;
        this.actividadRepository = actividadRepository;
    }

    public void cargaDeDatos(){
        crearCentrosConServicios();
    }

    public void crearCentrosConServicios(){
        NuevoCentroDTO primerCentroDTO = new NuevoCentroDTO("centro@mail","contra","Polideportivo Carrasco", "direccion2", "09973515", "encargado2","rut2","razonSocial2","Carrasco");
        NuevoCentroDTO segundoCentroDTO = new NuevoCentroDTO("centro2@mail","contra","centro2", "direccion3", "telefono3", "encargado3","rut3","razonSocial3","barrio2");
        CentroDeportivo primerCentro = centroService.saveNewCentro(primerCentroDTO);
        CentroDeportivo segundoCentro = centroService.saveNewCentro(segundoCentroDTO);

        agregarCanchasAPrimerCentro(primerCentro);

        Actividad newActividad1 = new Actividad(primerCentro,"Tenis",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad2 = new Actividad(primerCentro,"Tenis",DayOfWeek.SUNDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad3 = new Actividad(primerCentro,"Natacion",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad5 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad6 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.SUNDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad7 = new Actividad(segundoCentro,"Natacion2",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);

        Cancha newCancha4 = new Cancha(segundoCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,100);
        Cancha newCancha5 = new Cancha(segundoCentro,"cancha2",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10);
        Cancha newCancha6  = new Cancha(segundoCentro,"cancha_basquet2",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10);


        canchaRepository.save(newCancha4);
        canchaRepository.save(newCancha5);
        canchaRepository.save(newCancha6);

        actividadRepository.save(newActividad1);
        actividadRepository.save(newActividad2);
        actividadRepository.save(newActividad3);
        actividadRepository.save(newActividad5);
        actividadRepository.save(newActividad6);
        actividadRepository.save(newActividad7);
    }


    private void agregarCanchasAPrimerCentro(CentroDeportivo centroDeportivo){
        List<Cancha> canchas1 = new ArrayList<Cancha>(50);
        LocalTime hora = LocalTime.of(8, 0);
        LocalTime horaFin = hora.plusHours(1l);
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (long j = 0l; j<5l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Tenis 1", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 2", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 3", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 1", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 2", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 3", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
            }
        }
        dayOfWeek = DayOfWeek.SATURDAY;
        List<Cancha> cancha1FinDeSemana = new ArrayList<Cancha>(10);
        for (long j = 0l; j<1l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Tenis 1 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 2 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 3 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 1 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 2 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 3 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
            }
        }
        for (int i = 0; i< canchas1.size(); i++){
            canchaRepository.save(canchas1.get(i));
        }
        for (int i = 0; i< cancha1FinDeSemana.size(); i++){
            canchaRepository.save(cancha1FinDeSemana.get(i));
        }
    }

    private void agregarActividadesAPrimerCentro(CentroDeportivo centroDeportivo){
        List<Actividad> actividades1 = new ArrayList<Actividad>();

        LocalTime horaInicio = LocalTime.of(16, 30);
        LocalTime horaInicio2 = LocalTime.of(8, 30);


        //Añadiendo Tenis con dos horarios por día
        actividades1.add(new Actividad(centroDeportivo, "Tenis Principiante", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Tenis Principiante", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Tenis Principiante", DayOfWeek.TUESDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Tenis Principiante", DayOfWeek.THURSDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));



        horaInicio = horaInicio.plusMinutes(75l);

        actividades1.add(new Actividad(centroDeportivo, "Tenis Intermedio", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Tenis Intermedio", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        horaInicio = horaInicio.plusMinutes(75l);
        actividades1.add(new Actividad(centroDeportivo, "Tenis Avanzado", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Tenis Avanzado", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        for (int i = 0; i< actividades1.size(); i++){
            actividadRepository.save(actividades1.get(i));
        }

    }

}
