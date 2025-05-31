package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "producto_ingrediente")
public class ProductoIngrediente {

    /** id_producto_ingrediente INT AI PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_ingrediente")
    private Integer id;

    /** cantidad_utilizada DOUBLE */
    @Column(name = "cantidad_utilizada")
    private Double cantidadUtilizada;

    /** unidad VARCHAR(45) */
    private String unidad;

    /** FK -> producto.id */
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    /** FK -> ingrediente.id */
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}
