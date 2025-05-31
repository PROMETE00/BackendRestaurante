package com.restaurante.api.controller;

import com.restaurante.api.model.TipoProducto;
import com.restaurante.api.repository.TipoProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-producto")
@CrossOrigin(origins = "*")
public class TipoProductoController {

    private final TipoProductoRepository repo;

    public TipoProductoController(TipoProductoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<TipoProducto> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoProducto> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public TipoProducto create(@RequestBody TipoProducto tipo) {
        return repo.save(tipo);
    }

    @PutMapping("/{id}")
    public TipoProducto update(@PathVariable Integer id,
                               @RequestBody TipoProducto tipo) {
        tipo.setId(id);
        return repo.save(tipo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
