package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Projections.CuentaReservas;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;
import com.tictok.RUServidor.Entities.ReservaActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaActividadRepository extends JpaRepository<ReservaActividad, Long> {

    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaReservas(r.actividad.actividadId, count(r.actividad.cupos)) from ReservaActividad r
    where r.actividad.actividadId = ?1 and r.fecha = ?2
    group by r.actividad.actividadId
""")
    List<CuentaReservas> countReservasByServicioIdAndFecha(ServicioId servicioId, Date fecha);

    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaReservas(r.actividad.actividadId, count(r.actividad.cupos)) from ReservaActividad r
    where r.actividad.actividadId.nombreServicio = ?1 and r.actividad.actividadId.centroDeportivo = ?2
        and r.fecha between ?3 and ?4
    group by r.actividad.actividadId
    """)
    List<CuentaReservas> conseguirHorariosReservadosEntreFechas(String nombreServicio, String centroDeportivo, Date fechaStart, Date fechaEnd);

    @Query("select r from ReservaActividad r where r.usuario.cuenta.mail = ?1 and r.fecha >= ?2")
    List<ReservaActividad> findActividadesReservadasDespuesDe(String mail, Date fecha);

    @Query("select r from ReservaActividad r where r.usuario.cedula = ?1 and r.fecha between ?2 and ?3")
    List<ReservaActividad> conseguirReservasEntreFechasYDeUsuario(int cedula, Date fechaStart, Date fechaEnd);

    @Query("select r from ReservaActividad r where r.usuario.cedula = ?1")
    List<ReservaActividad> conseguirReservasDeUsuario(int cedula);



    //    @Query("""
//    select r from ReservaActividad r
//    where r.usuario.cedula = ?1
//        and r.fecha between ?3 and ?4
//    """)
//    List<ReservaActividad> conseguirReservasEntreFechasYDeUsuario(int cedula, Date fechaStart, Date fechaEnd);
//    Copie exactamente lo mismo pero anduvo

}