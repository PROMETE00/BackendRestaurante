package com.restaurante.api.controller;

import com.restaurante.api.model.EstadoMesa;
import com.restaurante.api.model.Mesa;
import com.restaurante.api.model.Mesero;
import com.restaurante.api.repository.EstadoMesaRepository;
import com.restaurante.api.repository.MesaRepository;
import com.restaurante.api.repository.MeseroRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    private final MesaRepository    mesaRepository;
    private final MeseroRepository  meseroRepository;
    private final EstadoMesaRepository estadoRepository;

    public MesaController(MesaRepository mesaRepository,
                          MeseroRepository meseroRepository,
                          EstadoMesaRepository estadoRepository) {
        this.mesaRepository   = mesaRepository;
        this.meseroRepository = meseroRepository;
        this.estadoRepository = estadoRepository;
    }

    /* ---------- CRUD básico ---------- */

    @GetMapping
    public List<Mesa> getAll() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mesa> getById(@PathVariable Integer id) {
        return mesaRepository.findById(id);
    }

    @PostMapping
    public Mesa create(@RequestBody Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @PutMapping("/{id}")
    public Mesa update(@PathVariable Integer id, @RequestBody Mesa mesa) {
        mesa.setId(id);
        return mesaRepository.save(mesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mesaRepository.deleteById(id);
    }

    /* ---------- Marcar mesa como atendida + asignar mesero ---------- */

    @PutMapping("/{id}/atender")
    public Mesa atenderMesa(@PathVariable Integer id, @RequestBody AsignarMeseroDTO dto) {

        Mesa   mesa   = mesaRepository  .findById(id)            .orElseThrow();
        Mesero mesero = meseroRepository.findById(dto.meseroId)  .orElseThrow();

        EstadoMesa atendida = estadoRepository
                .findByDescripcionIgnoreCase("atendida")
                .orElseThrow();   // asegúrate de tener este estado precargado

        mesa.setEstado(atendida);
        mesa.setMesero(mesero);

        return mesaRepository.save(mesa);
    }

    /* ---------- DTO ---------- */
    @Data
    public static class AsignarMeseroDTO {
        private Integer meseroId;
    }
}
