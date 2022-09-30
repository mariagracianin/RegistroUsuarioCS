package com.tictok.RUServidor.Services;

import com.tictok.Commons.CuentaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.CuentaYaExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        System.out.println("Constuctor Admin");
        crearPrimerAdministrador();
    }

    private void crearPrimerAdministrador(){
        Cuenta primerAdmin = new Cuenta("admin", "contra",
                null,"admin");
        cuentaRepository.save(primerAdmin);
        System.out.println("Administrador padre creado");
    }

    private void crearPrimeraEmpresa(){
        Cuenta primeraEmpresa = new Cuenta("empresa@mail", "contra",
                null,"empresa");
        cuentaRepository.save(primeraEmpresa);
        System.out.println("Empresa 1 creada");
    }

    public List<CuentaDTO> findAll() {
        List cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()){
            return null;
        }
        List cuentaDTOList = new ArrayList<CuentaDTO>(cuentaList.size());
        for (int i=0; i<cuentaList.size(); i++){
            Cuenta cuenta = (Cuenta) cuentaList.get(i);
            cuentaDTOList.add(CuentaMapper.toCuentaDTO(cuenta));
        }
        return cuentaDTOList;
    }

    public Cuenta save (CuentaDTO newCuentaDTO) throws CuentaYaExisteException {
        Cuenta newCuenta = CuentaMapper.toCuenta(newCuentaDTO);
        Optional<Cuenta> cuenta = cuentaRepository.findById(newCuenta.getId());

        if (cuenta.isPresent()){
            throw new CuentaYaExisteException();
        }
        return cuentaRepository.save(newCuenta);
    }

}
