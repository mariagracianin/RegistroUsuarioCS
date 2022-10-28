package com.tictok.RUServidor.Controllers;


import com.tictok.Commons.*;
import com.tictok.RUServidor.Exceptions.EntidadNoExisteException;
import com.tictok.RUServidor.Services.ActividadService;
import com.tictok.RUServidor.Services.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicio")
public class ServiciosController {

    private final CanchaService canchaService;
    private final ActividadService actividadService;

    @Autowired
    public ServiciosController(CanchaService canchaService, ActividadService actividadService) {
        this.canchaService = canchaService;
        this.actividadService = actividadService;
    }

    @GetMapping("/actividades")
    public List<SuperActividadDTO> getAllActividades(){
        return actividadService.findAll();
    }

    @GetMapping("/canchas")
    public List<SuperCanchaDTO> getAllCanchas(){
        return canchaService.findAll();
    }

    @GetMapping("/actividad/{centroDeportivo}/{actividadNombre}")
    public ActividadConHorariosYCuposDTO getActividadConHorariosYCuposDTO(@PathVariable String centroDeportivo, @PathVariable String actividadNombre) throws EntidadNoExisteException{
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------11");
        System.out.println(centroDeportivo+"  " + actividadNombre);
        return actividadService.getActividadConHorariosYCuposDTO(centroDeportivo, actividadNombre);
    }

    @GetMapping("/cancha/{centroDeportivo}/{canchaNombre}")
    public CanchaConHorariosYCuposDTO getCanchaConHorariosYCuposDTO(@PathVariable String centroDeportivo, @PathVariable String canchaNombre) throws EntidadNoExisteException {
        return canchaService.getCanchaConHorariosYCuposDTO(centroDeportivo, canchaNombre);
    }

    @GetMapping("actividades/{campoBusqueda}")
    public List<SuperActividadDTO> getActividadesBuscadas(@PathVariable String campoBusqueda){
        return actividadService.buscarActividades(campoBusqueda);
    }

    @GetMapping("canchas/{campoBusqueda}")
    public List<SuperCanchaDTO> getCanchasBuscadas(@PathVariable String campoBusqueda){
        return canchaService.buscarCanchas(campoBusqueda);
    }

}
