package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String tipo;

    @Column(precision = 10, scale = 2, nullable = false)
    private Double precio;
}
