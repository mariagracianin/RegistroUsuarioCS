package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.NuevoCentroDTO;
import com.tictok.Commons.SuperActividadDTO;
import com.tictok.Commons.SuperCanchaDTO;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Services.ActividadService;
import com.tictok.RUServidor.Services.CanchaService;
import com.tictok.RUServidor.Services.CentroService;
import com.tictok.RUServidor.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centro")
public class CentroController {

    private final CentroService centroService;
    private final ActividadService actividadService;
    private final CanchaService canchaService;

    @Autowired
    public CentroController(CentroService centroService, ActividadService actividadService, CanchaService canchaService) {
        this.centroService = centroService;
        this.actividadService = actividadService;
        this.canchaService = canchaService;
    }

    @PostMapping
    public void postNewCentro(@RequestBody NuevoCentroDTO nuevoCentroDTO){
        CentroDeportivo centro = centroService.saveNewCentro(nuevoCentroDTO);
    }

    @GetMapping("/actividades")
    public List<SuperActividadDTO> getAllActividades(){
        return actividadService.findAll();
    }

}
