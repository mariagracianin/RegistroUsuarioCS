package com.tictok.RUServidor.Controllers;

import com.tictok.Commons.*;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.*;
import com.tictok.RUServidor.Services.CuentaService;
import com.tictok.RUServidor.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final CuentaService cuentaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService, CuentaService cuentaService) {
        this.empresaService = empresaService;
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{mailEmpresa}/usuarios")
    public List<UsuarioDTO> getUsuariosFromEmpresa(@PathVariable String mailEmpresa) throws EmpresaNoExisteException, CuentaNoExisteException {
        return empresaService.findUsuariosFromEmpresa(cuentaService.findOnebyId(mailEmpresa).getEmpresa().getNombreEmpresa());
    }

    @PostMapping
    public void postNewEmpresa(@RequestBody NuevaEmpresaDTO nuevaEmpresaDTO){
        Empresa empresa = empresaService.saveNewEmpresa(nuevaEmpresaDTO);
    }

    @GetMapping("{mailEmpresa}/balance/{mes}/{year}")
    public List<UsuarioResumenDTO> getBalanceGeneral(@PathVariable String mailEmpresa,
                                                     @PathVariable int mes, @PathVariable int year) throws EntidadNoExisteException {
        return empresaService.getBalanceGeneral(mailEmpresa, mes, year);
    }

    @GetMapping("{mailEmpresa}/balanceUsuario/{cedulaUsuario}/{mes}/{year}")
    public List<CheckInDTO> getBalanceDeUsuario(@PathVariable String mailEmpresa, @PathVariable Integer cedulaUsuario,
                                    @PathVariable int mes, @PathVariable int year) throws EntidadNoExisteException, AccesoNoPermitidoException {
        return empresaService.getBalanceDeUsuario(mailEmpresa, cedulaUsuario, mes, year);
    }

    @PostMapping("/{mailEmpresa}/postCuenta")
    public void postNewCuenta(@PathVariable String mailEmpresa, @RequestBody CuentaDTO cuentaDTO) throws CuentaNoExisteException {
        empresaService.nuevaCuenta(mailEmpresa,cuentaDTO);
    }

}

