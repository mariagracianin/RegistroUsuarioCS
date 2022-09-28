package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String>{
}
