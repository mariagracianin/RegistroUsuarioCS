package com.tictok.RUServidor.Services;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Repositories.ActividadRepository;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;

    public CargaDeDatosService(EmpresaService empresaService, UsuarioService usuarioService, CentroService centroService, ActividadService actividadService, CanchaService canchaService, CanchaRepository canchaRepository, ActividadRepository actividadRepository, UsuarioRepository usuarioRepository) {
        this.empresaService = empresaService;
        this.usuarioService = usuarioService;
        this.centroService = centroService;
        this.actividadService = actividadService;
        this.canchaService = canchaService;
        this.canchaRepository = canchaRepository;
        this.actividadRepository = actividadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void cargaDeDatos() throws Exception {
        crear3Centros();
        crear3Empresas();
    }

    public void crear3Centros(){
        crearPrimerCentro();
        crearSegundoCentro();
        crearTercerCentro();
    }

    public void crear3Empresas() throws Exception {
        crearPrimeraEmpresa();
        crearSegundaEmpresa();
        crearTerceraEmpresa();
    }

    public void crearPrimerCentro(){
        NuevoCentroDTO primerCentroDTO = new NuevoCentroDTO("centro1@mail","contra1","Club de Tenis", "Av. Arcoiris", "101234567", "Felipe Montañes","rut1","razonSocial1","Carrasco");
        CentroDeportivo primerCentro = centroService.saveNewCentro(primerCentroDTO);
        agregarActividadesAPrimerCentro(primerCentro);
        agregarCanchasAPrimerCentro(primerCentro);
    }

    public void crearSegundoCentro(){
        NuevoCentroDTO segundoCentroDTO = new NuevoCentroDTO("centro2@mail","contra2","Club de Golf", "B. Unicornio", "201234567", "Ignacio Soler","rut2","razonSocial2","Pocitos");
        CentroDeportivo segundoCentro = centroService.saveNewCentro(segundoCentroDTO);
        agregarActividadesASegundoCentro(segundoCentro);
        agregarCanchasASegundoCentro(segundoCentro);
    }

    public void crearTercerCentro(){
        NuevoCentroDTO tercerCentroDTO = new NuevoCentroDTO("centro3@mail","contra3","Club de Pani", "Av. Caramelo", "301234567", "Clara Echeverria","rut3","razonSocial3","Malvin");
        CentroDeportivo tercerCentro = centroService.saveNewCentro(tercerCentroDTO);
        agregarActividadesATercerCentro(tercerCentro);
        agregarCanchasATercerCentro(tercerCentro);
    }

    public void crearPrimeraEmpresa() throws Exception {
        NuevaEmpresaDTO primeraEmpresaDTO = new NuevaEmpresaDTO("empresa1@mail","contra1","Pani Company", "Av. Panizza", "111111111", "Victoria Del Campo","rut4","razonSocial4");
        Empresa primeraEmpresa = empresaService.saveNewEmpresa(primeraEmpresaDTO);
        agregarEmpleadosAPrimeraEmpresa(primeraEmpresa);
    }

    public void crearSegundaEmpresa() throws Exception {
        NuevaEmpresaDTO segundaEmpresaDTO = new NuevaEmpresaDTO("empresa2@mail","contra2","Peewee Company", "Av. Del Campo", "2222222222", "Peewee (Vale Gonzalez)","rut5","razonSocial5");
        Empresa segundaEmpresa = empresaService.saveNewEmpresa(segundaEmpresaDTO);
        agregarEmpleadosASegundaEmpresa(segundaEmpresa);
    }

    public void crearTerceraEmpresa() throws Exception {
        NuevaEmpresaDTO terceraEmpresaDTO = new NuevaEmpresaDTO("empresa3@mail","contra3","JPA Company", "Av. TicTok", "333333333", "Rosina Varela","rut6","razonSocial6");
        Empresa terceraEmpresa = empresaService.saveNewEmpresa(terceraEmpresaDTO);
        agregarEmpleadosATerceraEmpresa(terceraEmpresa);
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

    private void agregarActividadesASegundoCentro(CentroDeportivo centroDeportivo){
        List<Actividad> actividades1 = new ArrayList<Actividad>();

        LocalTime horaInicio = LocalTime.of(15, 30);
        LocalTime horaInicio2 = LocalTime.of(7, 30);


        //Añadiendo Natacion con dos horarios por día
        actividades1.add(new Actividad(centroDeportivo, "Natacion", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Natacion", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Natacion", DayOfWeek.TUESDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Natacion", DayOfWeek.THURSDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));

        horaInicio = horaInicio.plusMinutes(75l);

        actividades1.add(new Actividad(centroDeportivo, "Clases de golf", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Clases de golf", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        horaInicio = horaInicio.plusMinutes(75l);
        actividades1.add(new Actividad(centroDeportivo, "Grupo de corredores", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Grupo de corredores", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        for (int i = 0; i< actividades1.size(); i++){
            actividadRepository.save(actividades1.get(i));
        }
    }

    private void agregarCanchasASegundoCentro(CentroDeportivo centroDeportivo){
        List<Cancha> canchas1 = new ArrayList<Cancha>(50);
        LocalTime hora = LocalTime.of(9, 0);
        LocalTime horaFin = hora.plusHours(1l);
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (long j = 0l; j<5l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Tenis A", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis B", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis C", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol A", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol B", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol C", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
            }
        }
        dayOfWeek = DayOfWeek.SATURDAY;
        List<Cancha> cancha1FinDeSemana = new ArrayList<Cancha>(10);
        for (long j = 0l; j<1l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Tenis A (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis B (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Tenis C (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol A (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol B (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol C (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
            }
        }
        for (int i = 0; i< canchas1.size(); i++){
            canchaRepository.save(canchas1.get(i));
        }
        for (int i = 0; i< cancha1FinDeSemana.size(); i++){
            canchaRepository.save(cancha1FinDeSemana.get(i));
        }
    }

    private void agregarActividadesATercerCentro(CentroDeportivo centroDeportivo){
        List<Actividad> actividades1 = new ArrayList<Actividad>();

        LocalTime horaInicio = LocalTime.of(14, 00);
        LocalTime horaInicio2 = LocalTime.of(6, 00);


        //Añadiendo Natacion con dos horarios por día
        actividades1.add(new Actividad(centroDeportivo, "Karate", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Karate", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Karate", DayOfWeek.TUESDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));
        actividades1.add(new Actividad(centroDeportivo, "Karate", DayOfWeek.THURSDAY,
                horaInicio2, horaInicio2.plusHours(1l), 150.0, 10, false));

        horaInicio = horaInicio.plusMinutes(75l);

        actividades1.add(new Actividad(centroDeportivo, "Ajedrez", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Ajedrez", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        horaInicio = horaInicio.plusMinutes(75l);
        actividades1.add(new Actividad(centroDeportivo, "Hockey", DayOfWeek.TUESDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));
        actividades1.add(new Actividad(centroDeportivo, "Hockey", DayOfWeek.THURSDAY,
                horaInicio, horaInicio.plusHours(1l), 150.0, 8, false));

        for (int i = 0; i< actividades1.size(); i++){
            actividadRepository.save(actividades1.get(i));
        }
    }

    private void agregarCanchasATercerCentro(CentroDeportivo centroDeportivo){
        List<Cancha> canchas1 = new ArrayList<Cancha>(50);
        LocalTime hora = LocalTime.of(9, 0);
        LocalTime horaFin = hora.plusHours(1l);
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        for (long j = 0l; j<5l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Waterpolo 1", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Waterpolo 2", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Polo 1", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Polo 2", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 5", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 800.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 7", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 500.0, 5));
            }
        }
        dayOfWeek = DayOfWeek.SATURDAY;
        List<Cancha> cancha1FinDeSemana = new ArrayList<Cancha>(10);
        for (long j = 0l; j<1l; j++){
            for (long i = 0l; i<10l; i++){
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 5 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 7 (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Fútbol 11", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 750.0, 5));
                canchas1.add(new Cancha(centroDeportivo, "Ping Pong (Fin de semana)", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 1", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
                canchas1.add(new Cancha(centroDeportivo, "Tenis 2", dayOfWeek.plus(j), hora.plusHours(i),horaFin.plusHours(i), 1000.0, 10));
            }
        }
        for (int i = 0; i< canchas1.size(); i++){
            canchaRepository.save(canchas1.get(i));
        }
        for (int i = 0; i< cancha1FinDeSemana.size(); i++){
            canchaRepository.save(cancha1FinDeSemana.get(i));
        }
    }

    private void agregarEmpleadosAPrimeraEmpresa(Empresa empresa) throws Exception {
        MegaUsuarioDTO megaUsuario1DTO = new MegaUsuarioDTO("agustin@mail", "contra", 52788472,"13/12/2023","Agustin", "Pani", "012345671",3000.0,500.0,3000.0,"Av.Italia");
        MegaUsuarioDTO megaUsuario2DTO = new MegaUsuarioDTO("mavi@mail", "contra",54967202,"14/12/2023","Victoria", "Del Campo", "012345672",3000.0,500.0,3000.0,"Uruguay");
        MegaUsuarioDTO megaUsuario3DTO = new MegaUsuarioDTO("maria@mail", "contra",52137396,"12/12/2023","Maria", "Nin", "012345673",3000.0,500.0,3000.0,"B.Artias");
        usuarioService.saveNewUsurio(megaUsuario1DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario2DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario3DTO, empresa.getCuentas().get(0).getMail());
    }

    private void agregarEmpleadosASegundaEmpresa(Empresa empresa) throws Exception {
        MegaUsuarioDTO megaUsuario1DTO = new MegaUsuarioDTO("valentina@mail", "contra", 52137311,"15/10/2023","Valentina", "Gonzalez", "012345674",2000.0,1000.0,2000.0,"B.Artias");
        MegaUsuarioDTO megaUsuario2DTO = new MegaUsuarioDTO("felipe@mail", "contra",52788412,"16/10/2023","Felipe", "Montañes", "012345675",2000.0,1000.0,2000.0,"Av.Italia");
        MegaUsuarioDTO megaUsuario3DTO = new MegaUsuarioDTO("mateo@mail", "contra",54967213,"17/10/2023","Mateo", "Patrone", "012345676",2000.0,1000.0,2000.0,"Uruguay");
        usuarioService.saveNewUsurio(megaUsuario1DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario2DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario3DTO, empresa.getCuentas().get(0).getMail());
    }

    private void agregarEmpleadosATerceraEmpresa(Empresa empresa) throws Exception {
        MegaUsuarioDTO megaUsuario1DTO = new MegaUsuarioDTO("hernan@mail", "contra", 52137500,"18/11/2023","Hernan", "Puschiasis", "012345677",1000.0,500.0,1000.0,"B.Artias");
        MegaUsuarioDTO megaUsuario2DTO = new MegaUsuarioDTO("rosina@mail", "contra",52788501,"19/11/2023","Rosina", "Varela", "012345678",1000.0,500.0,1000.0,"Av.Italia");
        MegaUsuarioDTO megaUsuario3DTO = new MegaUsuarioDTO("federico@mail", "contra",54967502,"20/11/2023","Federico", "Vazquez", "012345679",1000.0,500.0,1000.0,"Uruguay");
        usuarioService.saveNewUsurio(megaUsuario1DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario2DTO, empresa.getCuentas().get(0).getMail());
        usuarioService.saveNewUsurio(megaUsuario3DTO, empresa.getCuentas().get(0).getMail());
    }



}
