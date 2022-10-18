package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, ServicioId> {
}