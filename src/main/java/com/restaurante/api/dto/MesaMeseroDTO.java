// src/main/java/com/restaurante/api/dto/MesaMeseroDTO.java
package com.restaurante.api.dto;

import java.time.LocalDateTime;

/**
 * DTO para exponer únicamente la información del mesero asignado a una mesa.
 * Incluye: id de la asignación, id de mesero, nombre de mesero y fecha de asignación.
 */
public record MesaMeseroDTO(
    Integer id,
    Integer meseroId,
    String meseroNombre,
    LocalDateTime asignadoEn
) {}
