package com.restaurante.api.repository;

import com.restaurante.api.model.MesaMesero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaMeseroRepository extends JpaRepository<MesaMesero, Integer> {

    /* Devuelve todos los meseros que atienden una mesa */
    List<MesaMesero> findByMesaId(Integer mesaId);

    /* Devuelve todas las mesas que atiende un mesero */
    List<MesaMesero> findByMeseroId(Integer meseroId);
}
