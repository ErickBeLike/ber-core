package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.berraz.crm.core.producer.model.entity.Producer;

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
    private String tivId; // ID del CRM

    private String name;
    private String url;

    // Campos informativos
    private String category;            // Barrio Cerrado / Edificio
    
    @Column(name = "construction_phase")
    private String constructionPhase;   // Terminado / Pozo
    
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String observations;
    
    @Column(name = "possession_date")
    private String possessionDate;
    
    private String status;              // Activo / Inactivo
    
    @Column(name = "web_description", columnDefinition = "TEXT")
    private String webDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // --- JSONB (La magia para los detalles dinámicos) ---
    @Column(name = "details", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> details;

    // --- RELACIONES HIJAS (Uno a Muchos) ---
    // Cascade ALL: Si borras el development, se borran sus fotos, videos, etc.
    
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

    // --- RELACIONES 1 a 1 (Datos Satélite) ---
    
    @OneToOne(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DevelopmentMap map;

    @OneToOne(mappedBy = "development", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DevelopmentInternalData internalData;

    // ========================================================================
    // NUEVA RELACIÓN MUCHOS A MUCHOS CON PRODUCERS
    // ========================================================================
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "development_producers_link", // Nombre de la tabla intermedia en BD
        joinColumns = @JoinColumn(name = "development_id"), // FK hacia esta entidad
        inverseJoinColumns = @JoinColumn(name = "producer_id") // FK hacia la otra entidad
    )
    @ToString.Exclude // ¡IMPORTANTE! Para evitar bucles infinitos con Lombok
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Producer> assignedProducers = new ArrayList<>();
}