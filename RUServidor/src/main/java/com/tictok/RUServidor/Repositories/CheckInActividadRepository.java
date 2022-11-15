package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CheckInActividad;
import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Projections.CuentaCheckIns;
import com.tictok.RUServidor.Projections.CuentaReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.sql.Date;
import java.util.List;

public interface CheckInActividadRepository extends JpaRepository<CheckInActividad, Long> {
    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaCheckIns(r.actividad.actividadId, count(r.actividad.cupos)) from CheckInActividad r
    where r.actividad.actividadId = ?1 and r.fecha = ?2
    group by r.actividad.actividadId
    """)
    List<CuentaCheckIns> countCheckInsByServicioIdAndFecha(ServicioId servicioId, Date fecha);

    @Query("select c from CheckInActividad c where c.usuario.cedula = ?1 and c.fecha between ?2 and ?3")
    List<CheckInActividad> findByUsuario_CedulaAndFechaBetween(int cedula, Date fechaStart, Date fechaEnd);

    @Query("""
            select c from CheckInActividad c
            where c.actividad.centroDeportivo.nombreCentro = ?1 and c.fecha between ?2 and ?3""")
    List<CheckInActividad> getCheckInPorCentroYFechas(String nombreCentro, Date fechaStart, Date fechaEnd);

    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaCheckIns(c.actividad.actividadId, count(c.actividad.cupos)) from CheckInActividad c
    where c.actividad.actividadId.nombreServicio = ?1 and c.actividad.actividadId.centroDeportivo = ?2
        and c.fecha between ?3 and ?4
    group by c.actividad.actividadId
    """)
    List<CuentaCheckIns> conseguirHorariosReservadosEntreFechas(String nombreServicio, String centroDeportivo, Date fechaStart, Date fechaEnd);

}