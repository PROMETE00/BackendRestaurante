package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nombre;

    private Double cantidad;          // admite NULL → cantidad opcional

    @Column(length = 45)
    private String unidad;            // admite NULL → unidad opcional
}
