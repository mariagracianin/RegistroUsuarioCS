package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CheckInActividad;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Projections.CuentaCheckIns;
import com.tictok.RUServidor.Projections.CuentaReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CheckInActividadRepository extends JpaRepository<CheckInActividad, Long> {
    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaCheckIns(r.actividad.actividadId, count(r.actividad.cupos)) from CheckInActividad r
    where r.actividad.actividadId = ?1 and r.fecha = ?2
    group by r.actividad.actividadId
""")
    List<CuentaCheckIns> countCheckInsByServicioIdAndFecha(ServicioId servicioId, Date fecha);
}