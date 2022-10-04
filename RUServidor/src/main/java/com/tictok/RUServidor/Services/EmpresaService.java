package com.tictok.RUServidor.Services;

import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Exceptions.EmpresaNoExisteException;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import com.tictok.RUServidor.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
        System.out.println("Constuctor Empresa");
        crearPrimeraEmpresa();
    }


    private void crearPrimeraEmpresa(){
        Empresa empresa = new Empresa("empresa", "direccion", "telefono", "encargado");
        empresaRepository.save(empresa);
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
}
