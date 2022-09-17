package com.tictok.RUServidor.Usuario;

import com.tictok.RUServidor.Usuario.ErrorHandling.UsuarioMalDefinido;
import com.tictok.RUServidor.Usuario.ErrorHandling.UsuarioYaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Optional<Usuario> user = usuarioRepository.findById(newUsuario.getMail());

            if (!newUsuario.telefonoCorrecto()){
                throw new UsuarioMalDefinido();
            }
            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new UsuarioYaExisteException(newUsuario.getMail());
            } else{
                System.out.println("No lo encontro");
                return usuarioRepository.save(newUsuario);
                }

    }


}
