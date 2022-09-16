package com.tictok.RUServidor.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Usuario save(Usuario newUsuario) {
            Optional<Usuario> user = usuarioRepository.findById(newUsuario.getTelefono());

            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new com.tictok.RUServidor.Usuario.UsuarioYaExisteException(newUsuario.getTelefono());
            } else{
                System.out.println("No lo encontro");
                return usuarioRepository.save(newUsuario);
                }
    }

}
