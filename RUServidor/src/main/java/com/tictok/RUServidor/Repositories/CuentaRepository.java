package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String>{
    @Query(value = """
            select cd.nombre_centro as nombre, cd.address as address, cd.encargado as encargado,
            				cd.telefono as telefono, cd.rut as rut,
                             count((ci.id, ci.tipo)) as cantidad_check_ins, sum(ci.precio) as importe_total
                            from centro_deportivo cd
                          	left outer join (select cia.actividad_centrodeportivo as centro, cia.precio, cia.id, 'Actividad' as tipo
                                                from check_in_actividad cia
                              					where cia.fecha between :inicio and :fin
                                            union
                              				select cic.cancha_centrodeportivo as centro, cic.precio, cic.id, 'Cancha' as tipo
                                                from check_in_cancha cic
                              					where cic.fecha between :inicio and :fin)
                                as ci on ci.centro = cd.nombre_centro
                            group by cd.nombre_centro;
            		""",
            nativeQuery = true)
    List<Tuple> getBalanceAdminCentros(@Param("inicio") Date dateInicio, @Param("fin") Date dateFin);

    @Query(value = """
            select e.nombre_empresa as nombre, e.adress as address, e.encargado as encargado,
				e.telefono as telefono, e.rut as rut, count(u.cedula) as cantidad_usuarios,
                count((ci.id, ci.tipo)) as cantidad_check_ins, sum(ci.precio) as importe_total
                from empresa e
                left outer join usuario u on u.empresa_nombre_empresa = e.nombre_empresa
              	left outer join (select cia.usuario_cedula as usuario, cia.precio, cia.id, 'Actividad' as tipo
                                    from check_in_actividad cia
                  					where cia.fecha between :inicio and :fin
                                union
                  				select cic.usuario_cedula as usuario, cic.precio, cic.id, 'Cancha' as tipo
                                    from check_in_cancha cic
                  					where cic.fecha between :inicio and :fin)
                    as ci on ci.usuario = u.cedula\s
                group by e.nombre_empresa
                ;
            		""",
            nativeQuery = true)
    List<Tuple> getBalanceAdminEmpresas(@Param("inicio") Date dateInicio,
                                  @Param("fin") Date dateFin);

}