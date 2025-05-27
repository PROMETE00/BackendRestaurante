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
    public Optional<PedidoProducto> getById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    public PedidoProducto create(@RequestBody PedidoProducto pedidoProducto) {
        return repo.save(pedidoProducto);
    }

    @PutMapping("/{id}")
    public PedidoProducto update(@PathVariable String id, @RequestBody PedidoProducto pedidoProducto) {
        pedidoProducto.setIdPedidoProducto(id);
        return repo.save(pedidoProducto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
