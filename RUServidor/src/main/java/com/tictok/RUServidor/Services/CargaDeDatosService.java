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
        NuevoCentroDTO primerCentroDTO = new NuevoCentroDTO("centro@mail","contra","centro", "direccion2", "telefono2", "encargado2","rut2","razonSocial2","barrio");
        NuevoCentroDTO segundoCentroDTO = new NuevoCentroDTO("centro2@mail","contra","centro2", "direccion3", "telefono3", "encargado3","rut3","razonSocial3","barrio2");
        CentroDeportivo primerCentro = centroService.saveNewCentro(primerCentroDTO);
        CentroDeportivo segundoCentro = centroService.saveNewCentro(segundoCentroDTO);

        List<Cancha> cancha1 = new ArrayList<Cancha>(50);
        LocalTime hora = LocalTime.of(8, 0);
        LocalTime horaFin = hora.plusHours(1l);
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (long j = 0l; j<5l; j++){
            for (long i = 0l; i<10l; i++){
                cancha1.add(new Cancha(primerCentro, "Cancha 1 (Tenis)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
            }
        }
        Cancha newCancha1 = new Cancha(primerCentro,"Cancha de Tenis", DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,100);
        Cancha newCancha2 = new Cancha(primerCentro,"Cancha de Fútbol",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10);
        Cancha newCancha3 = new Cancha(primerCentro,"Cancha de Fútbol",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10);

        Actividad newActividad1 = new Actividad(primerCentro,"Tenis",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad2 = new Actividad(primerCentro,"Tenis",DayOfWeek.SUNDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad3 = new Actividad(primerCentro,"Natacion",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad5 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad6 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.SUNDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad7 = new Actividad(segundoCentro,"Natacion2",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);

        Cancha newCancha4 = new Cancha(segundoCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,100);
        Cancha newCancha5 = new Cancha(segundoCentro,"cancha2",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10);
        Cancha newCancha6  = new Cancha(segundoCentro,"cancha_basquet2",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10);

        for (int i = 0; i< cancha1.size(); i++){
            canchaRepository.save(cancha1.get(i));
        }
        canchaRepository.save(newCancha1);
        canchaRepository.save(newCancha2);
        canchaRepository.save(newCancha3);
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
}
