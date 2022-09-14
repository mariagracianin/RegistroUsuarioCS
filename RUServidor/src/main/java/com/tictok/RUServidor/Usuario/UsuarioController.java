package com.tictok.RUServidor.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuario() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario postNewUsuario(@RequestBody Usuario newUsuario) {
        return usuarioService.save(newUsuario);
    }






}
