package com.tictok.RUServidor.Services;

import com.tictok.RUServidor.Entities.Administrador;
import com.tictok.RUServidor.Repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
        System.out.println("Constuctor Admin");
        crearPrimerAdministrador();
    }

    private void crearPrimerAdministrador(){
        Administrador primerAdmin = new Administrador("admin", "contra",
                null,"admin");
        System.out.println("Administrador padre creado");
    }
}
