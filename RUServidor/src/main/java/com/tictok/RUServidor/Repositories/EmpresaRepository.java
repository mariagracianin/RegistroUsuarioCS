package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    @Query(value = """
            select u.cedula as cedula, u.nombre as nombre, u.apellido as apellido,
                        count(ci.precio) as cantidad_check_ins, sum(ci.precio) as importe_total,
                        u.saldo_base as saldo_base, u.sobregiro as sobregiro
                from usuario u
              	left outer join (select cia.usuario_cedula as cedula, cia.precio
                                    from check_in_actividad cia
                  					where cia.fecha between :inicio and :fin
                                union
                  				select cic.usuario_cedula as cedula, cic.precio
                                    from check_in_cancha cic
                  					where cic.fecha between :inicio and :fin)
                    as ci on ci.cedula = u.cedula
                where u.empresa_nombre_empresa = :nombre_empresa
                group by u.cedula
            		""",
            nativeQuery = true)
    List<Tuple> getBalanceEmpresa(@Param("inicio") Date dateInicio,
                                      @Param("fin") Date dateFin, @Param("nombre_empresa") String nombreEmpresa);
}
