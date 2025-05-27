package com.restaurante.api.controller;

import com.restaurante.api.model.Reserva;
import com.restaurante.api.repository.ReservaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaRepository repo;

    public ReservaController(ReservaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Reserva> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return repo.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        return repo.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
