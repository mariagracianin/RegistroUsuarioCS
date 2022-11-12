package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.CheckInCancha;
import com.tictok.RUServidor.Entities.ReservaCancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CheckInCanchaRepository extends JpaRepository<CheckInCancha, Long> {
    @Query("select c from CheckInCancha c where c.reservaCanchaPadre = ?1")
    List<CheckInCancha> findByReservaCanchaPadre(ReservaCancha reservaCanchaPadre);

    @Query("select c from CheckInCancha c where c.usuario.cedula = ?1 and c.fecha between ?2 and ?3")
    List<CheckInCancha> findByUsuario_CedulaAndFechaBetween(int cedula, Date fechaStart, Date fechaEnd);

}