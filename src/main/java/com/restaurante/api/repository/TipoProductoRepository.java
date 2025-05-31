package com.restaurante.api.repository;

import com.restaurante.api.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
}
