package com.restaurante.api.controller;

import com.restaurante.api.model.EstadoMesaEnum;
import com.restaurante.api.model.Mesa;
import com.restaurante.api.model.Mesero;
import com.restaurante.api.repository.MesaRepository;
import com.restaurante.api.repository.MeseroRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    private final MesaRepository   mesaRepository;
    private final MeseroRepository meseroRepository;

    public MesaController(
            MesaRepository mesaRepository,
            MeseroRepository meseroRepository
    ) {
        this.mesaRepository   = mesaRepository;
        this.meseroRepository = meseroRepository;
    }

    /* ---------- GET /api/mesas ---------- */
    @GetMapping
    public List<Mesa> getAll() {
        return mesaRepository.findAll();
    }

    /* ---------- GET /api/mesas/{id} ---------- */
    @GetMapping("/{id}")
    public Optional<Mesa> getById(@PathVariable Integer id) {
        return mesaRepository.findById(id);
    }

    /* ---------- POST /api/mesas ---------- */
    @PostMapping
    public Mesa create(@RequestBody Mesa mesa) {
        // Si no se envía "estado" en el JSON, toma por defecto EstadoMesaEnum.libre
        return mesaRepository.save(mesa);
    }

    /* ---------- PUT /api/mesas/{id} (actualiza TODO el objeto Mesa) ---------- */
    @PutMapping("/{id}")
    public Mesa update(@PathVariable Integer id, @RequestBody Mesa mesa) {
        mesa.setId(id);
        return mesaRepository.save(mesa);
    }

    /* ---------- PATCH /api/mesas/{id} (solo actualiza estado) ---------- */
    @PatchMapping("/{id}")
    public Mesa cambiarEstado(@PathVariable Integer id,
                              @RequestBody Map<String, String> body) {
        String nuevaDesc = body.get("estado");
        if (nuevaDesc == null) {
            throw new IllegalArgumentException("Debe incluir el campo 'estado' en el body");
        }

        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada: " + id));

        // Convertimos a minúsculas y quitamos espacios para que coincida con el nombre del enum
        String clave = nuevaDesc.trim().toLowerCase();

        EstadoMesaEnum nuevoEstado;
        try {
            nuevoEstado = EstadoMesaEnum.valueOf(clave);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Estado no válido: " + nuevaDesc);
        }

        mesa.setEstado(nuevoEstado);
        return mesaRepository.save(mesa);
    }

    /* ---------- DELETE /api/mesas/{id} ---------- */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        mesaRepository.deleteById(id);
    }

    /* ---------- PUT /api/mesas/{id}/atender 
          (asignar mesero + cambiar estado a “atendida”) ---------- */
    @PutMapping("/{id}/atender")
    public Mesa atenderMesa(@PathVariable Integer id,
                            @RequestBody AsignarMeseroDTO dto) {
        Mesa   mesa   = mesaRepository.findById(id).orElseThrow();
        Mesero mesero = meseroRepository.findById(dto.getMeseroId()).orElseThrow();

        mesa.setEstado(EstadoMesaEnum.atendida);
        mesa.setMesero(mesero);

        return mesaRepository.save(mesa);
    }

    @Data
    public static class AsignarMeseroDTO {
        private Integer meseroId;
    }
}
