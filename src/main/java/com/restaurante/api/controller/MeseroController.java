package com.restaurante.api.controller;

import com.restaurante.api.model.Mesero;
import com.restaurante.api.repository.MeseroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meseros")
@CrossOrigin(origins = "*")
public class MeseroController {

    private final MeseroRepository repo;

    public MeseroController(MeseroRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Mesero> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mesero> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public Mesero create(@RequestBody Mesero mesero) {
        return repo.save(mesero);
    }

    @PutMapping("/{id}")
    public Mesero update(@PathVariable Integer id, @RequestBody Mesero mesero) {
        mesero.setId(id);
        return repo.save(mesero);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
