package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, String>{
}
