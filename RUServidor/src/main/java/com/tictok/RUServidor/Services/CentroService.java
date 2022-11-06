package com.tictok.RUServidor.Services;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Exceptions.CuentaNoExisteException;
import com.tictok.RUServidor.Mappers.ActividadMapper;
import com.tictok.RUServidor.Mappers.CanchaMapper;
import com.tictok.RUServidor.Mappers.CentroMapper;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CentroService {

    private final CentroRepository centroRepository;
    private final CuentaRepository cuentaRepository;
    private final CanchaRepository canchaRepository;
    private final ActividadRepository actividadRepository;
    private final CuentaService cuentaService;
    private final ReservaActividadRepository reservaActividadRepository;
    private final ReservaCanchaRepository reservaCanchaRepository;

    private final CheckInActividadRepository checkInActividadRepository;

    @Autowired
    public CentroService(CentroRepository centroRepository, CuentaRepository cuentaRepository, CanchaRepository canchaRepository, ActividadRepository actividadRepository, CuentaService cuentaService, ReservaActividadRepository reservaActividadRepository, ReservaCanchaRepository reservaCanchaRepository, CheckInActividadRepository checkInActividadRepository) {
        this.centroRepository = centroRepository;
        this.cuentaRepository = cuentaRepository;
        this.canchaRepository = canchaRepository;
        this.actividadRepository = actividadRepository;
        this.cuentaService = cuentaService;
        this.reservaActividadRepository = reservaActividadRepository;
        this.reservaCanchaRepository = reservaCanchaRepository;
        this.checkInActividadRepository = checkInActividadRepository;
        crearPrimerCentro();

//        CentroDeportivo centroDeportivo = new CentroDeportivo("Coso", "Juan", "005262", "Juan2");
//        centroRepository.save(centroDeportivo);
//        System.out.println("Creamos centro");
//
//        DayOfWeek dia = LocalDate.MAX.getDayOfWeek();
//        LocalTime horaInicio = LocalTime.now();
//        Cancha cancha = new Cancha(centroDeportivo,"la canchita", dia, horaInicio, horaInicio, 100, 100);
//        canchaRepository.save(cancha);
//        System.out.println("Guardamos cancha");
    }

    public void crearPrimerCentro(){
        NuevoCentroDTO primerCentroDTO = new NuevoCentroDTO("centro@mail","contra","centro", "direccion2", "telefono2", "encargado2","rut2","razonSocial2","barrio");
        NuevoCentroDTO segundoCentroDTO = new NuevoCentroDTO("centro2@mail","contra","centro2", "direccion3", "telefono3", "encargado3","rut3","razonSocial3","barrio2");
        CentroDeportivo primerCentro = saveNewCentro(primerCentroDTO);
        CentroDeportivo segundoCentro = saveNewCentro(segundoCentroDTO);

        Cancha newCancha1 = new Cancha(primerCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,100);
        Cancha newCancha2 = new Cancha(primerCentro,"cancha_Fuchibol",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10);
        Cancha newCancha3 = new Cancha(primerCentro,"cancha_Fuchibol",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10);

        Actividad newActividad1 = new Actividad(primerCentro,"Tenis",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad2 = new Actividad(primerCentro,"Tenis",DayOfWeek.SUNDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad3 = new Actividad(primerCentro,"Natacion",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);
        Actividad newActividad5 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad6 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.SUNDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10,false);
        Actividad newActividad7 = new Actividad(segundoCentro,"Natacion2",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10,false);

        Cancha newCancha4 = new Cancha(segundoCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,100);
        Cancha newCancha5 = new Cancha(segundoCentro,"cancha2",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000.0,10);
        Cancha newCancha6  = new Cancha(segundoCentro,"cancha_basquet2",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000.0,10);

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

    public CentroDeportivo saveNewCentro(NuevoCentroDTO nuevoCentroDTO) {
        Cuenta cuenta = CuentaMapper.toCuentaFromNuevoCentroDTO(nuevoCentroDTO);
        CentroDeportivo centro = CentroMapper.toCentroFromNuevoCentroDTO(nuevoCentroDTO);

        centro.setCuenta(cuenta);
        cuentaRepository.save(cuenta);
        CentroDeportivo centro1 = centroRepository.save(centro);
        cuenta.setCentroDeportivo(centro);
        cuentaRepository.save(cuenta);
        return centro1;
    }

    public CentroDeportivo findById(String nombreCentro) throws Exception {
        Optional<CentroDeportivo> centro1 = centroRepository.findById(nombreCentro);
        if (centro1.isEmpty()){
            throw new Exception(); //Todo mandar cosos
        }
        return centro1.get();
    }

    @Transactional
    public List<SuperActividadDTO> getActividades(String mailCentro) throws CuentaNoExisteException {
        CentroDeportivo centro1 = cuentaService.findOnebyId(mailCentro).getCentroDeportivo();
        List<SuperActividadDTO> listaDTO = ActividadMapper.fromActividadesListToSuperActividadDTOList(centro1.getActividades());
        return listaDTO;
    }

    @Transactional
    public List<SuperCanchaDTO> getCanchas(String mailCentro) throws CuentaNoExisteException {
        CentroDeportivo centro1 = cuentaService.findOnebyId(mailCentro).getCentroDeportivo();
        List<SuperCanchaDTO> listaDTO = CanchaMapper.fromCanchaListToSuperCanchaDTOList(centro1.getCanchas());
        return listaDTO;
    }

    public CentroDeportivoDTO getCentroDeportivo(String mail) throws CuentaNoExisteException {
        CentroDeportivo centro = cuentaService.findOnebyId(mail).getCentroDeportivo();
        return new CentroDeportivoDTO(centro.getNombreCentro(),centro.getAddress(), centro.getBarrio(),centro.getTelefono(),centro.getEncargado(),centro.getRut(), centro.getRazonSocial());
    }

    @Transactional
    public List<ServicioResumenDTO> getBalanceCentro(String mail, int mes, int year) throws CuentaNoExisteException {
        CentroDeportivo centro = cuentaService.findOnebyId(mail).getCentroDeportivo();
        String nombreCentro = centro.getNombreCentro();
        LocalDate fecha = LocalDate.of(year, mes, 1);
//        Date start = Date.valueOf(fecha);

//        String fechaInicioStr = "'" +fecha.toString().replace('-', '/') + "'";
//        fechaInicioStr = fecha.toString().replace('-', '/');

        LocalDate fechaFin = fecha.plusMonths(1);
        Date fechaIni = Date.valueOf(fecha);
        Date fechaFi = Date.valueOf(fechaFin);
//        String fechaFinStr = "'" +fechaFin.toString().replace('-', '/') + "'";
//        fechaFinStr = fechaFin.toString().replace('-', '/');
//        System.out.println(fechaInicioStr);
//        System.out.println(fechaFinStr);
//        Date finish = Date.valueOf(fecha.withDayOfMonth(fecha.lengthOfMonth()));
//        List<CheckInActividad> checkInActividades = checkInActividadRepository.getCheckInPorCentroYFechas(nombreCentro, start, finish);
//        for (int i = 0; i<checkInActividades.size(); i++){
//            CheckInActividad checkInActividad = checkInActividades.get(i);
//            String nombreActividad = checkInActividad.getActividad().getActividadId().getNombreServicio();
//            String tipo = "coso";
//        }
        List<Tuple> tuplasBalances = checkInActividadRepository.getBalanceActividades(fechaIni, fechaFi, nombreCentro);
        List<ServicioResumenDTO> servicioResumenDTOList = new ArrayList<ServicioResumenDTO>(tuplasBalances.size());
        String nombreServicio;
        String tipo;
        int cantidadCheckIns;
        BigInteger temp;
        Double importeTotal;
        for (int i = 0; i<tuplasBalances.size(); i++){
            Tuple tupla = tuplasBalances.get(i);
            nombreServicio = (String) tupla.get("nombre_servicio");
            tipo = (String) tupla.get("tipo");
            temp = (BigInteger) tupla.get("cantidad_check_ins");
            cantidadCheckIns = temp.intValue();
            importeTotal = (Double) tupla.get("precio_total");
            ServicioResumenDTO servicioResumenDTO =
                    new ServicioResumenDTO(nombreServicio, tipo, cantidadCheckIns, importeTotal);
            servicioResumenDTOList.add(servicioResumenDTO);
        }
        return servicioResumenDTOList;
    }
}
