package com.berraz.crm.core.development.entity;

import com.berraz.crm.core.producer.model.entity.Producer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "developments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Development {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tiv_id", unique = true)
    private String tivId;

    private String name;
    private String url;
    private String category;
    
    @Column(name = "construction_phase")
    private String constructionPhase;
    
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String observations;
    
    @Column(name = "possession_date")
    private String possessionDate;
    
    private String status;
    
    @Column(name = "web_description", columnDefinition = "TEXT")
    private String webDescription;

    @Column(name = "details", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> details;

    // --- RELACIONES EXISTENTES ---
    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DevelopmentImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DevelopmentVideo> videos = new ArrayList<>();

    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DevelopmentPlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DevelopmentPublication> publications = new ArrayList<>();

    @OneToOne(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DevelopmentMap map;

    @OneToOne(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DevelopmentInternalData internalData;

    // ========================================================================
    // CAMBIO: RELACIÓN MUCHOS A UNO (N:1)
    // ========================================================================
    // Varios Developments tienen 1 Producer.
    // Esto crea la columna "producer_id" en la tabla "developments".
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id") 
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Producer producer;
}