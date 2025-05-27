package com.restaurante.api.controller;

import com.restaurante.api.model.Usuario;
import com.restaurante.api.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository repo;

    public UsuarioController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return repo.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario user) {
        Optional<Usuario> found = repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }
}
