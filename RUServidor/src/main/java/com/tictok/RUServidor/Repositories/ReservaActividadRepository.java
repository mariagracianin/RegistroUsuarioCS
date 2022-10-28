package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.NotTables.CuentaReservas;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.ReservaActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaActividadRepository extends JpaRepository<ReservaActividad, Long> {

    @Query("""
    select new com.tictok.RUServidor.Entities.NotTables.CuentaReservas(r.actividad.actividadId, count(r.actividad.actividadId)) from ReservaActividad r
    where r.actividad.actividadId = ?1 and r.fecha = ?2
    group by r.actividad.actividadId
""")
    List<CuentaReservas> countReservasByServicioIdAndFecha(ServicioId servicioId, Date fecha);

    @Query("""
    select new com.tictok.RUServidor.Entities.NotTables.CuentaReservas(r.actividad.actividadId, count(r.actividad.actividadId)) from ReservaActividad r
    where r.actividad.actividadId.nombreServicio = ?1 and r.actividad.actividadId.centroDeportivo = ?2
        and r.fecha between ?3 and ?4
    group by r.actividad.actividadId
    """)
    List<CuentaReservas> conseguirHorariosReservadosEntreFechas(String nombreServicio, String centroDeportivo, Date fechaStart, Date fechaEnd);

    @Query("select r from ReservaActividad r where r.usuario.cuenta.mail = ?1 and r.fecha >= ?2")
    List<ReservaActividad> findActividadesReservadasDespuesDe(String mail, Date fecha);





}