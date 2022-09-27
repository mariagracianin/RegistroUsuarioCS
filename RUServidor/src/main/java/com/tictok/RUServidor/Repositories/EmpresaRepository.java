package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
}
