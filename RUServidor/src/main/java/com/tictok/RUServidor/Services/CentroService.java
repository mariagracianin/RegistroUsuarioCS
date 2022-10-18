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
        CentroDeportivo primerCentro = saveNewCentro(primerCentroDTO);
        Cancha newCancha1 = new Cancha(primerCentro,"cancha Tenis",DayOfWeek.MONDAY,LocalTime.of(10,00,00),LocalTime.of(11,00,00),1000,10);
        Cancha newCancha2 = new Cancha(primerCentro,"cancha Fuchibol 1",DayOfWeek.MONDAY,LocalTime.of(10,00,00),LocalTime.of(11,00,00),1000,10);
        Cancha newCancha3 = new Cancha(primerCentro,"cancha Fuchibol 2",DayOfWeek.MONDAY,LocalTime.of(10,00,00),LocalTime.of(11,00,00),1000,10);
        Actividad newActividad1 = new Actividad(primerCentro,"Tenis",DayOfWeek.MONDAY, LocalTime.of(10,00,00),LocalTime.of(11,00,00),1000,10,false);
        Actividad newActividad2 = new Actividad(primerCentro,"Tenis",DayOfWeek.SUNDAY, LocalTime.of(10,00,00),LocalTime.of(11,00,00),1000,10,false);
        Actividad newActividad3 = new Actividad(primerCentro,"Natacion",DayOfWeek.MONDAY, LocalTime.of(9,00,00),LocalTime.of(10,00,00),1000,10,false);
        canchaRepository.save(newCancha1);
        canchaRepository.save(newCancha2);
        canchaRepository.save(newCancha3);
        actividadRepository.save(newActividad1);
        actividadRepository.save(newActividad2);
        actividadRepository.save(newActividad3);
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
}
