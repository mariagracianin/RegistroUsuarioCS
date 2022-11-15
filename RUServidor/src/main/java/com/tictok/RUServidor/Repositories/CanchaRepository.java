package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Cancha;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, ServicioId> {
    @Query("select c from Cancha c where c.canchaId.centroDeportivo = ?1 and c.canchaId.nombreServicio = ?2")
    List<Cancha> findByCentroAndNombre(String centroDeportivo, String nombreServicio);

    @Query("""
            select c from Cancha c
            where upper(c.canchaId.nombreServicio) like %:campoBusqueda% or upper(c.canchaId.centroDeportivo) like %:campoBusqueda%
                or upper(c.centroDeportivo.barrio) like %:campoBusqueda% or upper(c.centroDeportivo.address) like %:campoBusqueda%""")
    List<Cancha> findByNombreOBarrioIsLike(@Param("campoBusqueda") String campoBusqueda);


    @Query(value = """
        select distinct c.centro_deportivo_nombre_centro as nombreCentro, c.nombre_servicio as nombreCancha,
                  			c.precio as precio, c.imagen_id as imageId, cd.address as address,
                  			cd.barrio as barrio, cd.telefono as telefono
                  			from cancha c
                  			join centro_deportivo cd on c.centro_deportivo_nombre_centro = cd.nombre_centro""",
            nativeQuery = true
    )
    Page<Tuple> findDistinctBy(Pageable pageable);


    //Intentemos que funque
}