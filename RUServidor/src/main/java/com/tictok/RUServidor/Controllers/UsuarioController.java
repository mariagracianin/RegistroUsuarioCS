package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Services.CuentaService;
import com.tictok.RUServidor.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;
    private final CuentaService cuentaService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService, CuentaService cuentaService) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsuario() {
        return usuarioService.findAll();
    }

    @PostMapping
    public void postNewUsuario(@RequestBody MegaUsuarioDTO megaUsuarioDTO) throws CuentaYaExisteException {
        Usuario usuario = usuarioService.saveNewUsurio(megaUsuarioDTO);
    }






}
