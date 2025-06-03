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

    /* enum => se guarda como texto en la columna ENUM */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('pagado','pendiente','cancelado') default 'pendiente'")
    private EstatusPedido estatus = EstatusPedido.pendiente;   // valor por defecto

    @ManyToOne @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne @JoinColumn(name = "reservacion_id")
    private Reserva reservacion;
}
