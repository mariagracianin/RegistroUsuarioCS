package com.tictok.RUServidor.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
public class UsuarioController  {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public List<Usuario> getAllUsuario() {
        return usuarioService.findAll();
    }


    @PostMapping("/usuario")
    public Usuario postNewUsuario(@RequestBody Usuario newUsuario) {
        return usuarioService.save(newUsuario);
    }


}
