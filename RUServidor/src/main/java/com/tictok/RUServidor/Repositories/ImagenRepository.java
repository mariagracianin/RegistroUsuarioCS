package com.tictok.RUServidor.Repositories;

import com.tictok.RUServidor.Entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}