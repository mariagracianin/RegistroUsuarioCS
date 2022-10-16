package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.ReservaPosteriorAlInicioException;
import com.tictok.RUServidor.Exceptions.TipoDeReservaNoExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final CuentaService cuentaService;
    private final CanchaService canchaService;
    private final ActividadService actividadService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService, EmpresaService empresaService, CuentaService cuentaService, CanchaService canchaService, ActividadService actividadService) {
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.cuentaService = cuentaService;
        this.canchaService = canchaService;
        this.actividadService = actividadService;
    }

    @GetMapping("/all")
    public List<UsuarioDTO> getAllUsuario() {
        return usuarioService.findAll();
    }

    @GetMapping("{id}")
    public UsuarioDTO getUsuarioById(@PathVariable int id) throws UsuarioNoExisteException {
        return usuarioService.findOnebyId(id);
    }

    @PostMapping
    public void postNewUsuario(@RequestBody MegaUsuarioDTO megaUsuarioDTO) {
        Usuario usuario = usuarioService.saveNewUsurio(megaUsuarioDTO);
    }

    @GetMapping("{id}/reserva")
    public ReservaDTO getReservasByUsuario(){
        return null;
    }

    @PostMapping("/reserva")
    public ReservaDTO postNewReserva(@RequestBody ReservaDTO reservaDTO) throws TipoDeReservaNoExisteException, UsuarioNoExisteException, ReservaPosteriorAlInicioException {
        if (reservaDTO.getTipo().equals("Cancha")){
            return canchaService.reservarCancha(reservaDTO);
        } else if (reservaDTO.getTipo().equals("Actividad")) {
            return actividadService.reservarActividad(reservaDTO);
        }
        else {
            throw new TipoDeReservaNoExisteException();
        }
    }


    @DeleteMapping("/reserva/{idReserva}")
    public void DeleteReserva(@PathVariable Long idReserva){
    }








}
