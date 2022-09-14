package com.tictok.RUServidor.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public Usuario save(Usuario newUsuario) {
        System.out.println(newUsuario);
        try {
            usuarioRepository.getReferenceById(newUsuario.getTelefono());
            throw new UsuarioYaExisteException(newUsuario.getTelefono());
        }catch (EntityNotFoundException e){
            return usuarioRepository.save(newUsuario);
        }
    }

}
