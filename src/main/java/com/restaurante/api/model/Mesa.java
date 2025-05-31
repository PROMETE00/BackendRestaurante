package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;
    private Integer capacidad;

    @Column(length = 100)
    private String ubicacion;

    /** FK a la tabla estado_mesa */
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoMesa estado;

    /** FK opcional al mesero que la atiende */
    @ManyToOne
    @JoinColumn(name = "mesero_id")
    private Mesero mesero;
}
