package com.restaurante.api.controller;

import com.restaurante.api.model.Ingrediente;
import com.restaurante.api.repository.IngredienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteController {

    private final IngredienteRepository repo;

    public IngredienteController(IngredienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Ingrediente> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ingrediente> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public Ingrediente create(@RequestBody Ingrediente ingrediente) {
        return repo.save(ingrediente);
    }

    @PutMapping("/{id}")
    public Ingrediente update(@PathVariable Integer id, @RequestBody Ingrediente ingrediente) {
        ingrediente.setId(id);
        return repo.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
