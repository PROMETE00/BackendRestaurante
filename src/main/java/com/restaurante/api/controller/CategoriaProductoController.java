package com.restaurante.api.controller;

import com.restaurante.api.model.CategoriaProducto;
import com.restaurante.api.repository.CategoriaProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias-producto")
@CrossOrigin(origins = "*")
public class CategoriaProductoController {

    private final CategoriaProductoRepository repo;

    public CategoriaProductoController(CategoriaProductoRepository repo) {
        this.repo = repo;
    }

    // ─── GET: todas ──────────────────────────────────────────────────────────────
    @GetMapping
    public List<CategoriaProducto> getAll() {
        return repo.findAll();
    }

    // ─── GET: por id ─────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public Optional<CategoriaProducto> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    // ─── POST: crear ─────────────────────────────────────────────────────────────
    @PostMapping
    public CategoriaProducto create(@RequestBody CategoriaProducto categoria) {
        return repo.save(categoria);
    }

    // ─── PUT: actualizar ─────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public CategoriaProducto update(@PathVariable Integer id,
                                    @RequestBody CategoriaProducto categoria) {
        categoria.setId(id);
        return repo.save(categoria);
    }

    // ─── DELETE: eliminar ────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
