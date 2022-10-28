package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.Reserva2DTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.*;
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

    @PostMapping("{mail}")
    public void postNewUsuario(@PathVariable String mail, @RequestBody MegaUsuarioDTO megaUsuarioDTO) throws Exception {
        Usuario usuario = usuarioService.saveNewUsurio(megaUsuarioDTO, mail);
    }

    @GetMapping("{id}/reserva")
    public List<Reserva2DTO> getReservasByUsuario(@PathVariable String id){
        return usuarioService.getReservasByUsuario(id);
    }

    @PostMapping("/reserva")
    public ReservaDTO postNewReserva(@RequestBody Reserva2DTO reservaDTO)
            throws TipoDeReservaNoExisteException, UsuarioNoExisteException, ReservaPosteriorAlInicioException,
            CanchaYaReservadaException, ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuposAgotadosException, CuentaNoExisteException {
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
