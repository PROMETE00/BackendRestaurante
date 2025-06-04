package com.restaurante.api.controller;

import com.restaurante.api.model.Pedido;
import com.restaurante.api.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable("id") Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido nuevoPedido) {
        return pedidoRepository.save(nuevoPedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable("id") Integer id, @RequestBody Pedido pedidoActualizado) {
        pedidoActualizado.setId(id);
        return pedidoRepository.save(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable("id") Integer id) {
        pedidoRepository.deleteById(id);
    }
}
