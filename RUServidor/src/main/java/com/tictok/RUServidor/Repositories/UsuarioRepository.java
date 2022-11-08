package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CheckInActividad;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    @Query("select u from Usuario u where u.empresa = ?1")
    List<Usuario> findByEmpresa(@NonNull Empresa empresa);

}
