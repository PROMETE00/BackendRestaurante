// src/main/java/com/restaurante/api/controller/MesaMeseroController.java
package com.restaurante.api.controller;

import com.restaurante.api.dto.MesaMeseroDTO;
import com.restaurante.api.model.Mesa;
import com.restaurante.api.model.MesaMesero;
import com.restaurante.api.model.Mesero;
import com.restaurante.api.repository.MesaMeseroRepository;
import com.restaurante.api.repository.MesaRepository;
import com.restaurante.api.repository.MeseroRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;  
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador para gestionar las asignaciones mesero ⇆ mesa.
 * 
 * Endpoints:
 *  - GET    /api/mesas/{mesaId}/meseros     → lista de MeseroDTO asignados a la mesa
 *  - POST   /api/mesas/{mesaId}/meseros     → asigna un mesero a la mesa (cuerpo: { "meseroId": X })
 *  - DELETE /api/mesas/meseros/{mmId}       → elimina la asignación cuyo ID es mmId
 */
@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MesaMeseroController {

    private final MesaMeseroRepository mmRepo;
    private final MesaRepository       mesaRepo;
    private final MeseroRepository     meseroRepo;

    /**
     * GET  /api/mesas/{mesaId}/meseros
     * Devuelve, en forma de DTO ligero, los meseros asignados a la mesa {mesaId}.
     */
    @GetMapping("/{mesaId}/meseros")
    public List<MesaMeseroDTO> getMeserosDeMesa(@PathVariable Integer mesaId) {
        // Recupera todas las filas MesaMesero para esa mesa
        List<MesaMesero> lista = mmRepo.findByMesaId(mesaId);

        // Las mapea a MesaMeseroDTO para no exponer toda la entidad ni sus proxies internos
        return lista.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * POST /api/mesas/{mesaId}/meseros
     * Asigna el mesero cuyo ID viene en el body a la mesa {mesaId}.
     * Espera un JSON de la forma:
     *   { "meseroId": 5 }
     *
     * Al guardar, devuelve el registro de asignación convertido a DTO.
     */
  // MesaMeseroController.java  (solo el método POST cambia)
// MesaMeseroController.java   (solo el método POST cambia)
@PostMapping("/{mesaId}/meseros")
public MesaMeseroDTO asignarMesero(@PathVariable Integer mesaId,
                                   @RequestBody AsignarMeseroDTO body) {

    Integer meseroId = body.getMeseroId();

    // ——— entidades principales ———
    Mesa   mesa   = mesaRepo  .findById(mesaId)  .orElseThrow();
    Mesero mesero = meseroRepo.findById(meseroId).orElseThrow();

    /* ────────────────────────────────────────────────────────────
     * 1) Actualizamos la mesa para que `mesa.mesero_id` refleje
     *    al nuevo mesero (esto es lo que ve el front al recargar).
     * ──────────────────────────────────────────────────────────── */
    mesa.setMesero(mesero);
    mesaRepo.save(mesa);          // <- ahora el ticket mostrará el nuevo mesero

    /* ────────────────────────────────────────────────────────────
     * 2) Registramos la asignación en la tabla intermedia.
     *    - Si ya existe EXACTAMENTE la misma pareja, la devolvemos.
     *    - Si existe otro mesero para la misma mesa y solo quieres
     *      UNO vigente, puedes:
     *          a) borrar los anteriores,  o
     *          b) reutilizar el primero.
     *    Aquí dejamos historial, así que insertamos otro registro.
     * ──────────────────────────────────────────────────────────── */
    MesaMesero mm = mmRepo
        .findFirstByMesaIdAndMeseroId(mesaId, meseroId)
        .orElseGet(() -> {
            MesaMesero nuevo = new MesaMesero();
            nuevo.setMesa(mesa);
            nuevo.setMesero(mesero);
            return mmRepo.save(nuevo);
        });

    return toDTO(mm);   // 200 OK
}

    /**
     * DELETE /api/mesas/meseros/{mmId}
     * Desasigna (elimina) la fila intermedia que une a un mesero con una mesa.
     * mmId es el ID de la tabla "mesa_mesero".
     */
    @DeleteMapping("/meseros/{mmId}")
    public void desasignarMesero(@PathVariable Integer mmId) {
        mmRepo.deleteById(mmId);
    }

    /* ************************************************************************
     *                       Mappers privados (entidad → DTO)
     * ************************************************************************/
    private MesaMeseroDTO toDTO(MesaMesero mm) {
        return new MesaMeseroDTO(
                mm.getId(),
                mm.getMesero().getId(),
                mm.getMesero().getNombre(),
                mm.getAsignadoEn()
        );
    }

    /**
     * DTO auxiliar para leer el JSON de POST /api/mesas/{mesaId}/meseros
     */
    @Data
    public static class AsignarMeseroDTO {
        private Integer meseroId;
    }
}
