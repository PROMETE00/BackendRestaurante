package com.restaurante.api.controller;

import com.restaurante.api.model.ProductoIngrediente;
import com.restaurante.api.repository.ProductoIngredienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto-ingredientes")
@CrossOrigin(origins = "*")
public class ProductoIngredienteController {

    private final ProductoIngredienteRepository repo;

    public ProductoIngredienteController(ProductoIngredienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<ProductoIngrediente> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductoIngrediente> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public ProductoIngrediente create(@RequestBody ProductoIngrediente rel) {
        return repo.save(rel);
    }

    @PutMapping("/{id}")
    public ProductoIngrediente update(@PathVariable Integer id, @RequestBody ProductoIngrediente rel) {
        rel.setId(id);
        return repo.save(rel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
