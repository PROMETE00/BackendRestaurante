package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fecha;

    private LocalTime hora;

    private Double total;

    @Column(length = 50)
    private String estatus;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "reservacion_id")
    private Reserva reservacion;
}
