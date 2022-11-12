package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.BalanceDTO;
import com.tictok.RUServidor.Services.CuentaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CuentaService cuentaService;

    public AdminController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("balance/centrosDeportivos/{mes}/{year}")
    public List<BalanceDTO> getBalanceCentros(@PathVariable int mes, @PathVariable int year){
        return cuentaService.getBalanceCentros(mes, year);
    }

    @GetMapping("balance/empresas/{mes}/{year}")
    public List<BalanceDTO> getBalanceEmpresas(@PathVariable int mes, @PathVariable int year){
        return cuentaService.getBalanceEmpresas(mes, year);
    }
}
