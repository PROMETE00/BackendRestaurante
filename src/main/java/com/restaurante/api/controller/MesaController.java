package com.restaurante.api.controller;

import com.restaurante.api.model.Mesa;
import com.restaurante.api.repository.MesaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    private final MesaRepository repo;

    public MesaController(MesaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Mesa> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mesa> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public Mesa create(@RequestBody Mesa mesa) {
        return repo.save(mesa);
    }

    @PutMapping("/{id}")
    public Mesa update(@PathVariable Integer id, @RequestBody Mesa mesa) {
        mesa.setId(id);
        return repo.save(mesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
