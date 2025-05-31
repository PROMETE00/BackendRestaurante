package com.restaurante.api.controller;

import com.restaurante.api.model.MenuMesa;
import com.restaurante.api.repository.MenuMesaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menu-mesa")
@CrossOrigin(origins = "*")
public class MenuMesaController {

    private final MenuMesaRepository repo;

    public MenuMesaController(MenuMesaRepository repo) {
        this.repo = repo;
    }

    // 🔎 Listar todas las relaciones mesa–producto
    @GetMapping
    public List<MenuMesa> getAll() {
        return repo.findAll();
    }

    // 🔎 Obtener una relación por ID
    @GetMapping("/{id}")
    public Optional<MenuMesa> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    // ➕ Crear (asocia un producto a una mesa)
    @PostMapping
    public MenuMesa create(@RequestBody MenuMesa menuMesa) {
        return repo.save(menuMesa);
    }

    // ✏️ Actualizar
    @PutMapping("/{id}")
    public MenuMesa update(@PathVariable Integer id, @RequestBody MenuMesa menuMesa) {
        menuMesa.setId(id);
        return repo.save(menuMesa);
    }

    // ❌ Eliminar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
