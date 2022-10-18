package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, ServicioId> {
    @Query("select c from Cancha c where c.canchaId.centroDeportivo = ?1 and c.canchaId.nombreServicio = ?2")
    List<Cancha> findByCentroAndNombre(String centroDeportivo, String nombreServicio);

}