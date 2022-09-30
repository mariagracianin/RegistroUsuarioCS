package com.tictok.RUServidor.Services;

import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
        System.out.println("Constuctor Empresa");
        crearPrimeraEmpresa();
    }


    private void crearPrimeraEmpresa(){
        Empresa empresa = new Empresa("empresa", "direccion", "telefono", "encargado");
        empresaRepository.save(empresa);
    }
}
