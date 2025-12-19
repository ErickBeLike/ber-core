package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "development_publications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos de texto y estado
    @Column(name = "portal_name")
    private String portalName;       // ZonaProp, ArgenProp, etc.

    @Column(name = "listing_url")
    private String listingUrl;       // Link real al aviso

    @Column(name = "promotion_plan")
    private String promotionPlan;    // Super Destacado, Oro, etc.

    private String status;           // Online / Pausado
    
    @Column(name = "status_detail")
    private String statusDetail;     // "Finalizado por expiración", etc.

    // Fechas importantes
    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    // Relación con el padre
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "development_id")
    @ToString.Exclude
    private Development development;
}