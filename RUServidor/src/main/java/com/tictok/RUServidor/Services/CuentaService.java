package com.tictok.RUServidor.Services;

import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        System.out.println("Constuctor Admin");
        crearPrimerAdministrador();
    }

    private void crearPrimerAdministrador(){
        Cuenta primerAdmin = new Cuenta("admin", "contra",
                null,"admin");
        cuentaRepository.save(primerAdmin);
        System.out.println("Administrador padre creado");
    }

    private void crearPrimeraEmpresa(){
        Cuenta primeraEmpresa = new Cuenta("empresa@mail", "contra",
                null,"empresa");
        cuentaRepository.save(primeraEmpresa);
        System.out.println("Empresa 1 creada");
    }
}
