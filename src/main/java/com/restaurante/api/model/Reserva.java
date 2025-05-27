package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private LocalTime hora;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "mesero_id")
    private Mesero mesero;
}
