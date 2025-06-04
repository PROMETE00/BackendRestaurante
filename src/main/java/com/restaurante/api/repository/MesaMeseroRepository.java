// MesaMeseroRepository.java
package com.restaurante.api.repository;

import com.restaurante.api.model.MesaMesero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface MesaMeseroRepository extends JpaRepository<MesaMesero,Integer> {

    List<MesaMesero> findByMesaId(Integer mesaId);

    Optional<MesaMesero> findFirstByMesaIdAndMeseroId(Integer mesaId, Integer meseroId);
}
