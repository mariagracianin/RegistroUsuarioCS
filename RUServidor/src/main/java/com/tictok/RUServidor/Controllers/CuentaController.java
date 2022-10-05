package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuentaNoExisteException;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<CuentaDTO> getAllCuentas() {
        return cuentaService.findAll();
    }

    @PostMapping
    public CuentaDTO postNewCuenta(@RequestBody CuentaDTO newCuentaDTO) throws CuentaYaExisteException {
        String hola = "HOLA";
        return cuentaService.save(newCuentaDTO);
    }

    @GetMapping("/autenticar")
    public CuentaDTO autenticar(@RequestBody CuentaDTO cuentaDTOaAutenticar) throws CuentaNoExisteException {
        return cuentaService.autenticar(cuentaDTOaAutenticar);
    }
}
