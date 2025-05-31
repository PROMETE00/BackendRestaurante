package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EstadoMesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false, unique = true)
    private String descripcion;   // libre, reservada, ocupada, atendida…
}
