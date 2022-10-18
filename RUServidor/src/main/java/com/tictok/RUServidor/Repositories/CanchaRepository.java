package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, ServicioId> {
}