package com.tictok.RUServidor.Services;

import com.tictok.Commons.MegaUsuarioDTO;
import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Cuenta;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import com.tictok.RUServidor.Mappers.CuentaMapper;
import com.tictok.RUServidor.Repositories.CuentaRepository;
import com.tictok.RUServidor.Repositories.EmpresaRepository;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final EmpresaRepository empresaRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CuentaRepository cuentaRepository, EmpresaRepository empresaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
        this.empresaRepository = empresaRepository;
    }


    public List<UsuarioDTO> findAll() {
        List usuarioList = usuarioRepository.findAll();
        if (usuarioList.isEmpty()){
            return null;
        }

        List usuarioDTOList = new ArrayList<UsuarioDTO>(usuarioList.size());
        for (int i=0; i<usuarioList.size(); i++){
            Usuario user = (Usuario) usuarioList.get(i);
            usuarioDTOList.add(UsuarioMapper.toUsuarioDTO(user));
        }
        return usuarioDTOList;
    }


    public UsuarioDTO findOnebyId(Integer id) throws UsuarioNoExisteException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return UsuarioMapper.toUsuarioDTO(usuario.get());
        }
        else {
            throw new UsuarioNoExisteException(id);
        }
    }

    public List<Usuario> findbyId(List<Integer> idList){
        return usuarioRepository.findAllById(idList);
    }

    public Usuario save(UsuarioDTO newUsuarioDTO) {
        Usuario newUsuario = UsuarioMapper.toUsuario(newUsuarioDTO);
            Optional<Usuario> user = usuarioRepository.findById(newUsuario.getCedula());

            if (!newUsuario.telefonoCorrecto()){
                throw new UsuarioMalDefinido();
            }
            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new UsuarioYaExisteException(newUsuario.getStringCuenta());
            } else{
                System.out.println("No lo encontro");
                }

        return usuarioRepository.save(newUsuario);

    }


    public Usuario saveNewUsurio(MegaUsuarioDTO megaUsuarioDTO) {
        Cuenta cuenta = CuentaMapper.toCuentaFromMegaUsuarioDTO(megaUsuarioDTO);
        Usuario usuario = UsuarioMapper.toUsuarioFromMegaUsuarioDTO(megaUsuarioDTO);
        usuario.setCuenta(cuenta);
        cuenta.setUsuario(usuario);
        cuentaRepository.save(cuenta);
        Empresa empresa = empresaRepository.findAll().get(0);
        usuario.setEmpresa(empresa);
        return usuarioRepository.save(usuario);
    }

//    public List<UsuarioDTO> findByEmpresa(Empresa empresa) {
//        List usuariosList = usuarioRepository.findByEmpresa(empresa);
//        List usuarioDTOList = new ArrayList<UsuarioDTO>(usuariosList.size());
//        for (int i = 0; i<usuariosList.size(); i++){
//            usuarioDTOList.add(UsuarioMapper.toUsuarioDTO((Usuario) usuariosList.get(i)));
//        }
//        return usuarioDTOList;
//    }
}
