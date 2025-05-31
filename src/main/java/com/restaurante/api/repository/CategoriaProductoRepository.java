package com.restaurante.api.repository;

import com.restaurante.api.model.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
}

