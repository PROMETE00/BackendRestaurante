package com.restaurante.api.repository;

import com.restaurante.api.model.Mesero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeseroRepository extends JpaRepository<Mesero, Integer> {
}
