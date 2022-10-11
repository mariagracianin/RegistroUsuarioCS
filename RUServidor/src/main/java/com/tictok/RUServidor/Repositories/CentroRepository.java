package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CentroDeportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<CentroDeportivo, String> {
}
