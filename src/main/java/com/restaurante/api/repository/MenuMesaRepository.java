package com.restaurante.api.repository;

import com.restaurante.api.model.MenuMesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuMesaRepository extends JpaRepository<MenuMesa, Integer> {

    List<MenuMesa> findByMesaId(Integer mesaId);
}
