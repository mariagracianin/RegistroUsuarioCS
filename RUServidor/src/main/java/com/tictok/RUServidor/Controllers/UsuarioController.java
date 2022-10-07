package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Services.CuentaService;
import com.tictok.RUServidor.Services.EmpresaService;
import com.tictok.RUServidor.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final CuentaService cuentaService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService, EmpresaService empresaService, CuentaService cuentaService) {
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.cuentaService = cuentaService;
    }

    @GetMapping("/all")
    public List<UsuarioDTO> getAllUsuario() {
        return usuarioService.findAll();
    }

    @GetMapping("id/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable int id) throws UsuarioNoExisteException {
        return usuarioService.findOnebyId(id);
    }

    @PostMapping
    public void postNewUsuario(@RequestBody MegaUsuarioDTO megaUsuarioDTO) {
        Usuario usuario = usuarioService.saveNewUsurio(megaUsuarioDTO);
    }






}
