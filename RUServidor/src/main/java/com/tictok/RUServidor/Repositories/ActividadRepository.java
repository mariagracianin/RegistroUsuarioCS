package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, ServicioId> {
    @Query("""
            select a from Actividad a
            where upper(a.actividadId.nombreServicio) like upper(?1) or upper(a.actividadId.centroDeportivo) like upper(?2) or upper(a.centroDeportivo.barrio) like upper(?3) or upper(a.centroDeportivo.address) like upper(?4)""")
    List<Actividad> findByNombreOBarrioIsLike(String nombreServicio, String centroDeportivo, String barrio, String address);
}