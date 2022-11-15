package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.BalanceDTO;
import com.tictok.RUServidor.Services.CargaDeDatosService;
import com.tictok.RUServidor.Services.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CuentaService cuentaService;
    private final CargaDeDatosService cargaDeDatosService;

    public AdminController(CuentaService cuentaService, CargaDeDatosService cargaDeDatosService) {
        this.cuentaService = cuentaService;
        this.cargaDeDatosService = cargaDeDatosService;
    }

    @GetMapping("balance/centrosDeportivos/{mes}/{year}")
    public List<BalanceDTO> getBalanceCentros(@PathVariable int mes, @PathVariable int year){
        return cuentaService.getBalanceCentros(mes, year);
    }

    @GetMapping("balance/empresas/{mes}/{year}")
    public List<BalanceDTO> getBalanceEmpresas(@PathVariable int mes, @PathVariable int year){
        return cuentaService.getBalanceEmpresas(mes, year);
    }

    @PostMapping("cargaDeDatos")
    public void cargarDeDatos(){
        cargaDeDatosService.cargaDeDatos();
    }
}
