package com.restaurante.api.controller;

import com.restaurante.api.model.Cliente;
import com.restaurante.api.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return repo.save(cliente);
    }
}
