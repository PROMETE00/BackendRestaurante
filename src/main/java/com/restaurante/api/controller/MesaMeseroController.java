package com.restaurante.api.controller;

import com.restaurante.api.model.Mesa;
import com.restaurante.api.model.MesaMesero;
import com.restaurante.api.model.Mesero;
import com.restaurante.api.repository.MesaMeseroRepository;
import com.restaurante.api.repository.MesaRepository;
import com.restaurante.api.repository.MeseroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MesaMeseroController {

    private final MesaMeseroRepository mmRepo;
    private final MesaRepository mesaRepo;
    private final MeseroRepository meseroRepo;

    /* --- Obtener meseros asignados a una mesa --- */
    @GetMapping("/{mesaId}/meseros")
    public List<MesaMesero> getMeserosDeMesa(@PathVariable Integer mesaId) {
        return mmRepo.findByMesaId(mesaId);
    }

    /* --- Asignar mesero a mesa --- */
    @PostMapping("/{mesaId}/meseros")
    public MesaMesero asignarMesero(@PathVariable Integer mesaId,
                                    @RequestBody Map<String, Integer> body) {

        Integer meseroId = body.get("meseroId");
        Mesa   mesa   = mesaRepo.getReferenceById(mesaId);
        Mesero mesero = meseroRepo.getReferenceById(meseroId);

        MesaMesero mm = new MesaMesero();
        mm.setMesa(mesa);
        mm.setMesero(mesero);

        return mmRepo.save(mm);
    }

    /* --- Desasignar mesero (por id de la tabla intermedia) --- */
    @DeleteMapping("/meseros/{mmId}")
    public void desasignarMesero(@PathVariable Integer mmId) {
        mmRepo.deleteById(mmId);
    }
}
