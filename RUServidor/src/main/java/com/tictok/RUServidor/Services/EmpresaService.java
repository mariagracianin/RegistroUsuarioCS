package com.tictok.RUServidor.Services;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.NuevaEmpresaDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.EmpresaNoExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Mappers.EmpresaMapper;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import com.tictok.RUServidor.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository, CuentaRepository cuentaRepository) {
        this.empresaRepository = empresaRepository;
        this.cuentaRepository = cuentaRepository;
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
}
