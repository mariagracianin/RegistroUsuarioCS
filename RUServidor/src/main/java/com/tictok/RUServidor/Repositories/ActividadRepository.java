package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, ServicioId> {
    @Query("""
            select a from Actividad a
            where upper(a.actividadId.nombreServicio) like %:campoBusqueda% or upper(a.actividadId.centroDeportivo) like %:campoBusqueda% or
             upper(a.centroDeportivo.barrio) like %:campoBusqueda% or upper(a.centroDeportivo.address) like %:campoBusqueda%""")
    List<Actividad> findByNombreOBarrioIsLike(@Param("campoBusqueda") String campoBusqueda);
}