package com.restaurante.api.repository;

import com.restaurante.api.model.EstadoMesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoMesaRepository extends JpaRepository<EstadoMesa,Integer> {
    Optional<EstadoMesa> findByDescripcionIgnoreCase(String descripcion);
}
