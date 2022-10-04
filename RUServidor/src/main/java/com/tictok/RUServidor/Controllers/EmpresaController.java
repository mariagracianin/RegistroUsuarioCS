package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Exceptions.EmpresaNoExisteException;
import com.tictok.RUServidor.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/usuarios/{nombreEmpresa}")
    public List<UsuarioDTO> getUsuariosFromEmpresa(@PathVariable String nombreEmpresa) throws EmpresaNoExisteException {
        return empresaService.findUsuariosFromEmpresa(nombreEmpresa);
    }
}
