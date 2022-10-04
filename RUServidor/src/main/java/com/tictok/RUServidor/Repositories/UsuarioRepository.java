package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    @Query("select u from Usuario u where u.empresa = ?1")
    List<Usuario> findByEmpresa(@NonNull Empresa empresa);

}
