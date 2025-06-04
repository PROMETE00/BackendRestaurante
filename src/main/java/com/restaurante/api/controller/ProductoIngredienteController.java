package com.restaurante.api.controller;

import com.restaurante.api.model.ProductoIngrediente;
import com.restaurante.api.repository.ProductoIngredienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto-ingredientes")
@CrossOrigin(origins = "*")
public class ProductoIngredienteController {

    private ProductoIngredienteRepository productoIngredienteRepository;

    public ProductoIngredienteController(ProductoIngredienteRepository productoIngredienteRepository) {
        this.productoIngredienteRepository = productoIngredienteRepository;
    }

    @GetMapping
    public List<ProductoIngrediente> obtenerTodosLosRegistros() {
        return productoIngredienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductoIngrediente obtenerRegistroPorId(@PathVariable("id") Integer id) {
        return productoIngredienteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ProductoIngrediente crearRelacion(@RequestBody ProductoIngrediente nuevaRelacion) {
        return productoIngredienteRepository.save(nuevaRelacion);
    }

    @PutMapping("/{id}")
    public ProductoIngrediente actualizarRelacion(@PathVariable("id") Integer id, @RequestBody ProductoIngrediente relacionActualizada) {
        relacionActualizada.setId(id);
        return productoIngredienteRepository.save(relacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarRelacion(@PathVariable("id") Integer id) {
        productoIngredienteRepository.deleteById(id);
    }
}
