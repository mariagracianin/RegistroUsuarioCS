package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Exceptions.EmpresaNoExisteException;
import com.tictok.RUServidor.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{nombreEmpresa}/usuarios")
    public List<UsuarioDTO> getUsuariosFromEmpresa(@PathVariable String nombreEmpresa) throws EmpresaNoExisteException {
        return empresaService.findUsuariosFromEmpresa(nombreEmpresa);
    }

    @PostMapping
    public void postNewEmpresa(@RequestBody NuevaEmpresaDTO nuevaEmpresaDTO){
        Empresa empresa = empresaService.saveNewEmpresa(nuevaEmpresaDTO);
    }
}
