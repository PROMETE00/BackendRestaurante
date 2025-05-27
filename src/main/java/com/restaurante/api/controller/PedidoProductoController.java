package com.restaurante.api.controller;

import com.restaurante.api.model.PedidoProducto;
import com.restaurante.api.repository.PedidoProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido-productos")
@CrossOrigin(origins = "*")
public class PedidoProductoController {

    private final PedidoProductoRepository repo;

    public PedidoProductoController(PedidoProductoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<PedidoProducto> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PedidoProducto> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public PedidoProducto create(@RequestBody PedidoProducto pp) {
        return repo.save(pp);
    }

    @PutMapping("/{id}")
    public PedidoProducto update(@PathVariable Integer id, @RequestBody PedidoProducto pp) {
        pp.setId(id);
        return repo.save(pp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
