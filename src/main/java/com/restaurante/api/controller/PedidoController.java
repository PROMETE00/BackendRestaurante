package com.restaurante.api.controller;

import com.restaurante.api.model.Pedido;
import com.restaurante.api.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;
import com.restaurante.api.model.EstatusPedido;
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

    // Agregar método PATCH para actualizar solo el estatus
    @PatchMapping("/{id}")
    public Pedido updateStatus(@PathVariable Integer id, @RequestBody String estatus) {
    // Buscar el pedido por ID
    Pedido pedido = repo.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

    // Convertir el String a su valor correspondiente en el enum
    try {
        pedido.setEstatus(EstatusPedido.valueOf(estatus.toUpperCase()));  // Asignamos el estatus
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Estatus no válido");
    }

    // Guardar el pedido con el nuevo estatus
    return repo.save(pedido);
}


}
