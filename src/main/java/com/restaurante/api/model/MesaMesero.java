// src/main/java/com/restaurante/api/model/MesaMesero.java
package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(
  name = "mesa_mesero",
  uniqueConstraints = @UniqueConstraint(name = "uk_mesa_mesero", columnNames = {"mesa_id", "mesero_id"})
)
public class MesaMesero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* --- Relaciones --- */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesero_id", nullable = false)
    private Mesero mesero;

    /* --- Auditoría --- */
    @Column(name = "asignado_en", nullable = false)
    private LocalDateTime asignadoEn = LocalDateTime.now();
}
