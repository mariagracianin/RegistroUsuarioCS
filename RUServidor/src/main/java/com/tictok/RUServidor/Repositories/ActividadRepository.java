package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Actividad;
import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.NotTables.ServicioIdSinHorario;
import com.tictok.RUServidor.Projections.ActividadInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.SqlResultSetMapping;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Objects;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, ServicioId> {

    @Query("select a from Actividad a where a.actividadId.centroDeportivo = ?1 and a.actividadId.nombreServicio = ?2")
    List<Actividad> findByCentroAndNombre(String centroDeportivo, String nombreServicio);

    @Query("""
            select a from Actividad a
            where upper(a.actividadId.nombreServicio) like %:campoBusqueda% or upper(a.actividadId.centroDeportivo) like %:campoBusqueda% or
             upper(a.centroDeportivo.barrio) like %:campoBusqueda% or upper(a.centroDeportivo.address) like %:campoBusqueda%""")
    List<Actividad> findByNombreOBarrioIsLike(@Param("campoBusqueda") String campoBusqueda);

    @Query(value = """
        select distinct a.centro_deportivo_nombre_centro as nombreCentro, a.nombre_servicio as nombreActividad,
                  			a.pase_libre as paseLibre, a.precio as precio, a.imagen_id as imageId, cd.address as address,
                  			cd.barrio as barrio, cd.telefono as telefono
                  			from actividad a
                  			join centro_deportivo cd on a.centro_deportivo_nombre_centro = cd.nombre_centro""",
        nativeQuery = true
        )
    List<Tuple> findDistinctBy(Pageable pageable);



}