package com.restaurante.api.repository;

import com.restaurante.api.model.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, String> {
}
