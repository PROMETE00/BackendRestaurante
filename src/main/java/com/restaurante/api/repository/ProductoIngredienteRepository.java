package com.restaurante.api.repository;

import com.restaurante.api.model.ProductoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoIngredienteRepository extends JpaRepository<ProductoIngrediente, Integer> {
}
