package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.*;
import com.tictok.RUServidor.Entities.CentroDeportivo;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Services.ActividadService;
import com.tictok.RUServidor.Services.CanchaService;
import com.tictok.RUServidor.Services.CentroService;
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

    @PostMapping("/postActividad/{mailCentro}")
    public void postNewActividad(@RequestBody NuevoServicioDTO nuevaActividadDTO, @PathVariable String mailCentro) throws CuentaNoExisteException {
        actividadService.guardarActividad(nuevaActividadDTO, mailCentro);
    }

    @PostMapping("/postCancha/{mailCentro}")
    public void postNewCancha(@RequestBody NuevoServicioDTO nuevaCanchaDTO, @PathVariable String mailCentro) throws CuentaNoExisteException {
        canchaService.guardarCancha(nuevaCanchaDTO,mailCentro);
    }

    @GetMapping("/getActividades/{mailCentro}")
    public List<SuperActividadDTO> getActividades(@PathVariable String mailCentro) throws  CuentaNoExisteException{
        return centroService.getActividades(mailCentro);
    }

    @GetMapping("/getCanchas/{mailCentro}")
    public List<SuperCanchaDTO> getCanchas(@PathVariable String mailCentro) throws  CuentaNoExisteException{
        return centroService.getCanchas(mailCentro);
    }

    @GetMapping("/obtenerCentro/{mailCentro}")
    public CentroDeportivoDTO getCentroFromMail(@PathVariable String mailCentro) throws  CuentaNoExisteException{
        return centroService.getCentroDeportivo(mailCentro);
    }

    @PostMapping("/checkIn")
    public void postNewCheckInSinReserva(@RequestBody CheckInDTO checkInDTO) throws CuentaNoExisteException, UsuarioNoExisteException, CuposAgotadosException, TipoDeCheckInNoExisteException {
        if (checkInDTO.getTipo().equals("Cancha")){
            //actividadService.checkInCancha(checkInDTO)-->sin hacer aun
        } else if (checkInDTO.getTipo().equals("Actividad")) {
            System.out.println("cedula maria: " + checkInDTO.getCedulaUsuario() + "----------------------------------------------------");
            actividadService.checkInActividadSinReserva(checkInDTO);
        }
        else {
            throw new TipoDeCheckInNoExisteException();
        }
    }
}
