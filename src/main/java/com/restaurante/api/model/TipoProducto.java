package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_producto")   // ⇦  asegura coincidencia con la tabla
@Data
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;
}
