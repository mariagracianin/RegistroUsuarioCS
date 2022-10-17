package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.ReservaCancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalTime;

public interface ReservaCanchaRepository extends JpaRepository<ReservaCancha, Long> {
    @Query("""
            select (count(r) > 0) from ReservaCancha r
            where r.fecha = ?1 and r.cancha.canchaId.horaInicio = ?2 and r.cancha.canchaId.centroDeportivo = ?3 and r.cancha.canchaId.nombreServicio = ?4""")
    boolean existsReservaConCanchaHoraYFecha(Date fecha, LocalTime horaInicio, String centroDeportivo, String nombreServicio);

}