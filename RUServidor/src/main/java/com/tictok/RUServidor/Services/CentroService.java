package com.tictok.RUServidor.Services;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Mappers.CentroMapper;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.EmpresaMapper;
import com.tictok.RUServidor.Repositories.ActividadRepository;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import com.tictok.RUServidor.Repositories.CentroRepository;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CentroService {

    private final CentroRepository centroRepository;
    private final CuentaRepository cuentaRepository;
    private final CanchaRepository canchaRepository;
    private final ActividadRepository actividadRepository;

    @Autowired
    public CentroService(CentroRepository centroRepository, CuentaRepository cuentaRepository, CanchaRepository canchaRepository, ActividadRepository actividadRepository) {
        this.centroRepository = centroRepository;
        this.cuentaRepository = cuentaRepository;
        this.canchaRepository = canchaRepository;
        this.actividadRepository = actividadRepository;
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

        Cancha newCancha1 = new Cancha(primerCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000,100);
        Cancha newCancha2 = new Cancha(primerCentro,"cancha_Fuchibol",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000,10);
        Cancha newCancha3 = new Cancha(primerCentro,"cancha_Fuchibol",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000,10);

        Actividad newActividad1 = new Actividad(primerCentro,"Tenis",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000,10,false);
        Actividad newActividad2 = new Actividad(primerCentro,"Tenis",DayOfWeek.SUNDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000,10,false);
        Actividad newActividad3 = new Actividad(primerCentro,"Natacion",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000,10,false);
        Actividad newActividad5 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.MONDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000,10,false);
        Actividad newActividad6 = new Actividad(segundoCentro,"Tenis2",DayOfWeek.SUNDAY, LocalTime.of(10,00),LocalTime.of(11,00),1000,10,false);
        Actividad newActividad7 = new Actividad(segundoCentro,"Natacion2",DayOfWeek.MONDAY, LocalTime.of(9,00),LocalTime.of(10,00),1000,10,false);

        Cancha newCancha4 = new Cancha(segundoCentro,"cancha_Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000,100);
        Cancha newCancha5 = new Cancha(segundoCentro,"cancha2",DayOfWeek.MONDAY,LocalTime.of(10,00),LocalTime.of(11,00),1000,10);
        Cancha newCancha6  = new Cancha(segundoCentro,"cancha_basquet2",DayOfWeek.MONDAY,LocalTime.of(9,00),LocalTime.of(10,00),1000,10);

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
}
