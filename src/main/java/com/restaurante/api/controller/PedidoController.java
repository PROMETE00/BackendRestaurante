package com.restaurante.api.controller;

import com.restaurante.api.model.Pedido;
import com.restaurante.api.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoRepository repo;

    public PedidoController(PedidoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Pedido> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> getById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido) {
        return repo.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return repo.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
