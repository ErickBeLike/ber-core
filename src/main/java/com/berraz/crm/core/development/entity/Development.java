package com.berraz.crm.core.development.entity;

import com.berraz.crm.core.producer.model.entity.Producer;
import com.berraz.crm.core.property.model.entity.Property;

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


@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "property_development_link",            // <--- 1. Nombre EXACTO de tu tabla en BD
        joinColumns = @JoinColumn(name = "development_id"), // <--- 2. Nombre de la columna FK hacia Development
        inverseJoinColumns = @JoinColumn(name = "property_id") // <--- 3. Nombre de la columna FK hacia Property
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Property> properties = new ArrayList<>();
}