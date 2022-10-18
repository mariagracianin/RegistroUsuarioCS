package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Empresa;
import com.tictok.RUServidor.Entities.ReservaActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalTime;

@Repository
public interface ReservaActividadRepository extends JpaRepository<ReservaActividad, Long> {
//    long countReservasActividadConFechaYHora1(String nombreServicio, String centroDeportivo, LocalTime horaInicio, Date fecha);

//    long countReservasActividadConFechaYHora(String nombreServicio, String centroDeportivo, LocalTime horaInicio, Date fecha);
}