package com.tictok.RUServidor.Services;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Mappers.CentroMapper;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.EmpresaMapper;
import com.tictok.RUServidor.Repositories.CanchaRepository;
import com.tictok.RUServidor.Repositories.CentroRepository;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CentroService {

    private final CentroRepository centroRepository;
    private final CuentaRepository cuentaRepository;
    private final CanchaRepository canchaRepository;

    @Autowired
    public CentroService(CentroRepository centroRepository, CuentaRepository cuentaRepository, CanchaRepository canchaRepository) {
        this.centroRepository = centroRepository;
        this.cuentaRepository = cuentaRepository;
        this.canchaRepository = canchaRepository;

        CentroDeportivo centroDeportivo = new CentroDeportivo("Coso", "Juan", "005262", "Juan2");
        centroRepository.save(centroDeportivo);
        System.out.println("Creamos centro");

        DayOfWeek dia = LocalDate.MAX.getDayOfWeek();
        LocalTime horaInicio = LocalTime.now();
        Cancha cancha = new Cancha(centroDeportivo,"la canchita", dia, horaInicio, horaInicio, 100, 100);
        canchaRepository.save(cancha);
        System.out.println("Guardamos cancha");
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
