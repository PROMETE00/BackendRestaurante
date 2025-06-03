// src/main/java/com/restaurante/api/dto/MenuMesaDTO.java
package com.restaurante.api.dto;

import java.math.BigDecimal;

public record MenuMesaDTO(
        Integer id,
        Integer mesaId,
        Integer productoId,
        String  nombre,
        BigDecimal precio        // <──  BigDecimal
) {}
