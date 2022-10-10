package com.tictok.RUServidor.Services;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Mappers.CentroMapper;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.EmpresaMapper;
import com.tictok.RUServidor.Repositories.CentroRepository;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroService {

    private final CentroRepository centroRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public CentroService(CentroRepository centroRepository, CuentaRepository cuentaRepository) {
        this.centroRepository = centroRepository;
        this.cuentaRepository = cuentaRepository;
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
