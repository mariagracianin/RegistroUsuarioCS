package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.CheckInDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.ReservaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.ReservaCancha;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Mappers.ReservaMapper;
import com.tictok.RUServidor.Repositories.ReservaCanchaRepository;
import com.tictok.RUServidor.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;
    //private final EmpresaService empresaService;
    //private final CuentaService cuentaService;
    private final CanchaService canchaService;
    private final ActividadService actividadService;
    //private final ReservaCanchaRepository reservaCanchaRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, CanchaService canchaService, ActividadService actividadService) {
        this.usuarioService = usuarioService;
        //this.empresaService = empresaService;
        //this.cuentaService = cuentaService;
        this.canchaService = canchaService;
        this.actividadService = actividadService;
        //this.reservaCanchaRepository = reservaCanchaRepository;
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
        usuarioService.saveNewUsurio(megaUsuarioDTO, mail);
    }

    @GetMapping("{id}/reserva")
    public List<ReservaDTO> getReservasByUsuario(@PathVariable String id){
        return usuarioService.getReservasByUsuario(id);
    }

    @PostMapping("/reserva")
    public ReservaDTO postNewReserva(@RequestBody ReservaDTO reservaDTO)
            throws TipoDeReservaNoExisteException, UsuarioNoExisteException, ReservaPosteriorAlInicioException,
            CanchaYaReservadaException, ReservaPadreNoExisteException, ReservaPosteriorAlFinException, CuposAgotadosException, CuentaNoExisteException {
        System.out.println("mail: " +reservaDTO.getMailUsuario() + "nombre actividad: "+ reservaDTO.getNombreActividad() );
        if (reservaDTO.getTipo().equals("Cancha")){

            return canchaService.reservarCancha(reservaDTO);

        } else if (reservaDTO.getTipo().equals("Actividad")) {
            return actividadService.reservarActividad(reservaDTO);
        }
        else {
            throw new TipoDeReservaNoExisteException();
        }
    }

    @GetMapping("/getReservas/{mailUsuario}")
    public List<ReservaDTO> getReservasFromMail(@PathVariable String mailUsuario) throws CuentaNoExisteException {
        return usuarioService.getReservasActuales(mailUsuario);
    }

    @GetMapping("/getReservasByCedula/{cedula}/{mailCentro}")
    public List<ReservaDTO> getReservasFromCedulaAndCentroDeportivo(@PathVariable String cedula, @PathVariable String mailCentro) throws CuentaNoExisteException {
        return usuarioService.getReservasUsuarioByCedulaAndCentro(Integer.parseInt(cedula), mailCentro);
    }

    @DeleteMapping("/deleteReserva/{tipo}/{idReserva}")
    public void DeleteReserva(@PathVariable Long idReserva, @PathVariable String tipo) throws Exception {
        if(tipo.equals("Cancha")){
            canchaService.cancelarReserva(idReserva);
        }else if (tipo.equals("Actividad")){
            actividadService.cancelarReserva(idReserva);
        }
    }

    @GetMapping("/getReservaCanchaFromCodigo/{codigoPadre}")
    public ReservaDTO getReservaCanchaFromCodigo(@PathVariable String codigoPadre) throws Exception {
        return usuarioService.getReservaCanchaFromCodigo(codigoPadre);
    }

    @GetMapping("/getCheckIns/{cedulaUsuario}/{mes}/{year}")
    public List<CheckInDTO> getCheckIns(@PathVariable int cedulaUsuario, @PathVariable int mes, @PathVariable int year){
        return usuarioService.getCheckIns(cedulaUsuario,mes,year);
    }

}
