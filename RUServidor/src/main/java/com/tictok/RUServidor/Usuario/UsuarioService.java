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
        boolean isFound = true;
        try {
            Usuario user = usuarioRepository.getReferenceById(newUsuario.getTelefono());
            System.out.println("El telefono es" + user.getDirec());
        }catch (EntityNotFoundException e){
            System.out.println("Llega aca");
            isFound = false;
        }
        if (isFound){
            System.out.println("Lo encontro");
            throw new UsuarioYaExisteException(newUsuario.getTelefono());
        }else {
            System.out.println("No lo encontro");
            return usuarioRepository.save(newUsuario);
        }
    }

}
