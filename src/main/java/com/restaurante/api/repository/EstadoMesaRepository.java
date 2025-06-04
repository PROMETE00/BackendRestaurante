package com.restaurante.api.repository;

import com.restaurante.api.model.EstadoMesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoMesaRepository extends JpaRepository<EstadoMesa, Integer> {
    // findFirst… para que nunca devuelva “varios resultados”
    EstadoMesa findFirstByDescripcionIgnoreCase(String descripcion);
}
