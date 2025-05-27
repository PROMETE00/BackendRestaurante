package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PedidoProducto {

    @Id
    @Column(name = "id_pedido_producto", length = 45)
    private String idPedidoProducto;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
