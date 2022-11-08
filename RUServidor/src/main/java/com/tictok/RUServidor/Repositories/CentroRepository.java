package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CentroDeportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

@Repository
public interface CentroRepository extends JpaRepository<CentroDeportivo, String> {

    @Query(value = """
            select cia.actividad_nombreservicio as nombre_servicio,
                   'Actividad' as tipo, count(cia.id) as cantidad_check_ins,
                    sum(cia.precio) as precio_total
               		from check_in_actividad as cia
                    where cia.fecha between :inicio and :fin
                    and cia.actividad_centrodeportivo =:centro    	
                    group by nombre_servicio
            union
            select cic.cancha_nombreservicio as nombre_servicio,
            		'Cancha' as tipo, count(cic.id) as cantidad_check_ins,
            		sum(cic.precio) as precio_total
            		from check_in_cancha as cic
            		where cic.fecha between :inicio and :fin
            		and cic.cancha_centrodeportivo =:centro
            		group by nombre_servicio
            		""",
            nativeQuery = true)
    List<Tuple> getBalanceActividades(@Param("inicio") Date dateInicio,
                                      @Param("fin") Date dateFin, @Param("centro") String centroDeportivo);
}
