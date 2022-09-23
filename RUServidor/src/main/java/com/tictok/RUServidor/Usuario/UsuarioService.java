package com.tictok.RUServidor.Usuario;

import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Usuario.ErrorHandling.UsuarioMalDefinido;
import com.tictok.RUServidor.Usuario.ErrorHandling.UsuarioNoExisteException;
import com.tictok.RUServidor.Usuario.ErrorHandling.UsuarioYaExisteException;
import com.tictok.RUServidor.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public Usuario findOnebyId(String id) throws UsuarioNoExisteException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        else {
            throw new UsuarioNoExisteException();
        }
    }

    public List<Usuario> findbyId(List<String> idList){
        return usuarioRepository.findAllById(idList);
    }

    public Usuario save(UsuarioDTO newUsuarioDTO) {
        Usuario newUsuario = UsuarioMapper.toUsuario(newUsuarioDTO);
            /*Optional<Usuario> user = usuarioRepository.findById(newUsuario.getMail());

            if (!newUsuario.telefonoCorrecto()){
                throw new UsuarioMalDefinido();
            }
            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new UsuarioYaExisteException(newUsuario.getMail());
            } else{
                System.out.println("No lo encontro");
                return usuarioRepository.save(newUsuario);
                }*/
        return usuarioRepository.save(newUsuario);

    }


}
