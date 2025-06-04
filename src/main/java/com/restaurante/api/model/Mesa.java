package com.restaurante.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;
    private Integer capacidad;

    @Column(length = 50, nullable = false)
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('libre','reservada','ocupada','atendida')")
    private EstadoMesaEnum estado = EstadoMesaEnum.libre;
    // Este campo corresponde directamente a la columna "estado" en la tabla "mesa".

    @ManyToOne
    @JoinColumn(name = "mesero_id", foreignKey = @ForeignKey(name = "fk_mesa_mesero"), nullable = true)
    private Mesero mesero;
    
    // (Opcional) Si antes tenías getEstadoDescripcion(), ahora es un simple getter de enum:
    public String getEstadoDescripcion() {
        return (estado != null) ? estado.name().toLowerCase() : "libre";
    }
}
