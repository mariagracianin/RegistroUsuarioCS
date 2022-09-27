package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
