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

    private String ubicacion;
    
    private Integer capacidad;

    @Column(length = 20)
    private String estado = "libre"; // valores: libre, reservada, ocupada, atendida

    @ManyToOne
    @JoinColumn(name = "mesero_id")
    private Mesero mesero;
}
