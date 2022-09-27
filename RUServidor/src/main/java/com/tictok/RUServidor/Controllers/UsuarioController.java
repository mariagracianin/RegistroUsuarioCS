package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Nos de un USUARIODTO y no un USUARIO
    /*@GetMapping
    public List<Usuario> getAllUsuario() {
        return usuarioService.findAll();
    }*/

    @PostMapping
    public Usuario postNewUsuario(@RequestBody UsuarioDTO newUsuarioDTO) {
        return usuarioService.save(newUsuarioDTO);
    }






}
