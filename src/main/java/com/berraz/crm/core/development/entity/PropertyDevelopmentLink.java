package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_development_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDevelopmentLink {

    // Usamos una clave compuesta o un ID simple. 
    // Como tu tabla no tiene un campo 'id' serial propio (según el reporte), 
    // usaremos @IdClass o IDs embebidos.
    // PERO, para simplificar al máximo y evitar errores de Hibernate con claves compuestas:
    
    @EmbeddedId
    private PropertyDevelopmentLinkId id;
    
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt; // Solo lectura
}

// Necesitas esta clase extra para la clave compuesta
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class PropertyDevelopmentLinkId implements java.io.Serializable {
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "development_id")
    private Long developmentId;
}