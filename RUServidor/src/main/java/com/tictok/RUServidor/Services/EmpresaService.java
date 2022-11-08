package com.tictok.RUServidor.Services;

import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.Resumenes.UsuarioResumenDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.*;
import com.tictok.RUServidor.Exceptions.EmpresaNoExisteException;
import com.tictok.RUServidor.Exceptions.EntidadNoExisteException;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.EmpresaMapper;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import com.tictok.RUServidor.Repositories.EmpresaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final CuentaRepository cuentaRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository, CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository) {
        this.empresaRepository = empresaRepository;
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
        System.out.println("Constuctor Empresa");
        crearPrimeraEmpresa();
    }


    private void crearPrimeraEmpresa(){
        NuevaEmpresaDTO primeraEmpresaDTO = new NuevaEmpresaDTO("empresa@mail","contra","empresa", "direccion", "telefono", "encargado","rut","razonSocial");
        saveNewEmpresa(primeraEmpresaDTO);
    }

    public Empresa findById(String nombreEmpresa) throws Exception {
        Optional<Empresa> empresa1 = empresaRepository.findById(nombreEmpresa);
        if (empresa1.isEmpty()){
            throw new Exception(); //Todo mandar cosos
        }
        return empresa1.get();
    }

    public List<UsuarioDTO> findUsuariosFromEmpresa(String nombreEmpresa) throws EmpresaNoExisteException {
        Optional<Empresa> empresa = empresaRepository.findById(nombreEmpresa);
        if (empresa.isEmpty()) throw new EmpresaNoExisteException(nombreEmpresa);
        Empresa empresa1 = empresa.get();
        return UsuarioMapper.toUsuarioDTOList(empresa1.getUsuarios());
    }

    public Empresa saveNewEmpresa(NuevaEmpresaDTO nuevaEmpresaDTO) {
        Cuenta cuenta = CuentaMapper.toCuentaFromNuevaEmpresaDTO(nuevaEmpresaDTO);
        Empresa empresa = EmpresaMapper.toEmpresaFromNuevaEmpresaDTO(nuevaEmpresaDTO);

        empresa.setCuenta(cuenta);
        cuentaRepository.save(cuenta);
        Empresa empresa1 = empresaRepository.save(empresa);
        cuenta.setEmpresa(empresa);
        cuentaRepository.save(cuenta);
        return empresa1;
    }

    public List<UsuarioResumenDTO> getBalanceGeneral(String mailEmpresa, int mes, int year) throws EntidadNoExisteException {

        Optional<Cuenta> cuentaEmpresa = cuentaRepository.findById(mailEmpresa);
        if (cuentaEmpresa.isEmpty()) {
            System.out.println("No encontre la empresa");
            throw new EntidadNoExisteException("La empresa con ese mail no existe");
        }

        LocalDate fecha = LocalDate.of(year, mes, 1);
        LocalDate fechaFinal = fecha.plusMonths(1);
        Date fechaInicio = Date.valueOf(fecha);
        Date fechaFin = Date.valueOf(fechaFinal);

        Empresa empresa = cuentaEmpresa.get().getEmpresa();

        List<Tuple> tuplasBalances = empresaRepository.getBalanceEmpresa(fechaInicio, fechaFin, empresa.getNombreEmpresa());
        List<UsuarioResumenDTO> usuarioResumenDTOList = new ArrayList<UsuarioResumenDTO>(tuplasBalances.size());

        int cedula;
        String nombre;
        String apellido;
        int cantidadCheckIns;
        BigInteger temp;
        double importe;
        double saldoBase;
        double saldo;
        double sobregiro;
        for (int i = 0; i<tuplasBalances.size(); i++){
            Tuple tupla = tuplasBalances.get(i);
            cedula = (int) tupla.get("cedula");
            nombre = (String) tupla.get("nombre");
            apellido = (String) tupla.get("apellido");
            temp = (BigInteger) tupla.get("cantidad_check_ins");
            cantidadCheckIns = temp.intValue();
            try {
                importe = (double) tupla.get("importe_total");
            } catch  (NullPointerException n){
                importe = 0.0;
            }
            saldoBase = (double) tupla.get("saldo_base");
            saldo = saldoBase - importe;
            sobregiro = (double) tupla.get("sobregiro");
            usuarioResumenDTOList.add(new
                    UsuarioResumenDTO(cedula, nombre, apellido, cantidadCheckIns,
                        importe, saldoBase, saldo, sobregiro));
        }
        return usuarioResumenDTOList;
    }
}
