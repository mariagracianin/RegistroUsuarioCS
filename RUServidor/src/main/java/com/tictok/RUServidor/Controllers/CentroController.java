package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Services.CentroService;
import com.tictok.RUServidor.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/centro")
public class CentroController {

    private final CentroService centroService;

    @Autowired
    public CentroController(CentroService centroService) {
        this.centroService = centroService;
    }

    @PostMapping
    public void postNewEmpresa(@RequestBody NuevoCentroDTO nuevoCentroDTO){
        CentroDeportivo centro = centroService.saveNewCentro(nuevoCentroDTO);
    }
}
