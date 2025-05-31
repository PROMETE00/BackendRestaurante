package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu_mesa")
@Data
public class MenuMesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* --- Relaciones --- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
