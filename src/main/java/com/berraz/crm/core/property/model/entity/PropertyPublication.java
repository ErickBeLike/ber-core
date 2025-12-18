package com.berraz.crm.core.property.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_publications")
@Data
public class PropertyPublication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- ESTADO GENERAL ---
    @Column(name = "context_state")
    private String contextState; // Ej: ACTIVO, INACTIVO

    @Column(name = "publication_state")
    private String publicationState; // Ej: Publicado, Quitado, Finalizado

    @Column(name = "status_detail")
    private String statusDetail; // Detalles extra del estado

    // --- DATOS DEL PORTAL ---
    @Column(name = "portal_name")
    private String portalName; // ZonaProp, ArgenProp, MercadoLibre

    @Column(name = "promotion_plan")
    private String promotionPlan; // Destacado, Super, Simple

    @Column(name = "listing_url", columnDefinition = "TEXT")
    private String listingUrl; // Link directo al aviso

    @Column(name = "last_published_at")
    private LocalDateTime lastPublishedAt;

    // --- CONDICIONES ECONÓMICAS (Snapshot) ---
    // Guardamos el precio al momento de publicar (histórico)
    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "operation_currency")
    private String operationCurrency;

    @Column(name = "operation_price")
    private BigDecimal operationPrice;

    // --- RELACIÓN ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @ToString.Exclude
    private Property property;
}