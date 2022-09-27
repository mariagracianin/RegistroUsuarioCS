package com.tictok.RUServidor.Services;

import com.tictok.Commons.UsuarioDTO;
import com.tictok.RUServidor.Entities.Usuario;
import com.tictok.RUServidor.Exceptions.UsuarioMalDefinido;
import com.tictok.RUServidor.Exceptions.UsuarioYaExisteException;
import com.tictok.RUServidor.Repositories.UsuarioRepository;
import com.tictok.RUServidor.Exceptions.UsuarioNoExisteException;
import com.tictok.RUServidor.Mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        System.out.println("Constuctor ");
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
        System.out.println(newUsuario.getMail());
            Optional<Usuario> user = usuarioRepository.findById(newUsuario.getMail());

            if (!newUsuario.telefonoCorrecto()){
                throw new UsuarioMalDefinido();
            }
            if (user.isPresent()){
                System.out.println("Lo encontro");
                throw new UsuarioYaExisteException(newUsuario.getMail());
            } else{
                System.out.println("No lo encontro");
                }

        return usuarioRepository.save(newUsuario);

    }


}
