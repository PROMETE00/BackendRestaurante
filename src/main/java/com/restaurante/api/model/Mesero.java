package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Mesero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private LocalDate fechaNacimiento;

    private String turno;

    private Double salario;

    public enum Sexo {
        M, F
    }
}
