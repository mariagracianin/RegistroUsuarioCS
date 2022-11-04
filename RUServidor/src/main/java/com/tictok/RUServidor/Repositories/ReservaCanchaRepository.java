package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.ReservaActividad;
import com.tictok.RUServidor.Projections.CuentaReservas;
import com.tictok.RUServidor.Entities.ReservaCancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaCanchaRepository extends JpaRepository<ReservaCancha, Long> {
    @Query("""
            select (count(r) > 0) from ReservaCancha r
            where r.fecha = ?1 and r.cancha.canchaId.horaInicio = ?2 and r.cancha.canchaId.centroDeportivo = ?3 and r.cancha.canchaId.nombreServicio = ?4""")
    boolean existsReservaConCanchaHoraYFecha(Date fecha, LocalTime horaInicio, String centroDeportivo, String nombreServicio);

    @Query("""
    select new com.tictok.RUServidor.Projections.CuentaReservas(r.cancha.canchaId, 1L) from ReservaCancha r
    where r.cancha.canchaId.nombreServicio = ?1 and r.cancha.canchaId.centroDeportivo = ?2 and r.fecha between ?3 and ?4
    group by r.cancha.canchaId
    """)
    List<CuentaReservas> conseguirHorariosReservadosEntreFechas(String nombreServicio, String centroDeportivo, Date fechaStart, Date fechaEnd);

    @Query("""
    select r from ReservaCancha r
    where r.usuario.cuenta.mail = ?1 and r.fecha  >= ?2
    """)
    List<ReservaCancha> findCanchasReservadasDespuesDe(String mail, Date fecha);

    @Query("select r from ReservaCancha r where r.usuario.cedula = ?1 and r.fecha between ?2 and ?3")
    List<ReservaCancha> conseguirReservasEntreFechasYDeUsuario(int cedula, Date fechaStart, Date fechaEnd);

    @Query("select r from ReservaCancha r where r.usuario.cedula = ?1")
    List<ReservaCancha> conseguirReservasDeUsuario(int cedula);
}
