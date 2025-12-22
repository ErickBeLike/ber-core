package com.berraz.crm.core.property.model.entity;

import com.berraz.crm.core.common.model.AuditableEntity;
import com.berraz.crm.core.common.util.converter.StringListConverter;
import com.berraz.crm.core.common.util.converter.StringMapConverter;
import com.berraz.crm.core.development.entity.Development;
import com.berraz.crm.core.producer.model.entity.Producer;
import com.berraz.crm.core.owner.model.entity.Owner;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // Importante: incluye ID y campos de auditoría en el hash
public class Property extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- IDENTIFICADORES ---
    @Column(unique = true)
    private String code; // Ej: BER1257

    // --- INFO PRINCIPAL ---
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "reference_text", columnDefinition = "TEXT")
    private String referenceText; // Campo nuevo detectado en CSV

    private String type; // Casa, Lote, etc.
    private String location;
    private String zoning;

    @Column(name = "status")
    private String status; // ACTIVE, INACTIVE, etc.

    // --- OPERACIÓN Y PRECIOS ---
    ///@Column(name = "operation_type")
    ///private String operationType;

    ///private String currency; // U$S
    ///private BigDecimal price;
    private String expenses;

    // --- DETALLES ---
    private String antiquity;

    @Column(name = "condition_state")
    private String conditionState;

    private String orientation;

    // --- SUPERFICIES ---
    @Column(name = "total_area")
    private String totalArea;

    @Column(name = "covered_area")
    private String coveredArea;

    @Column(name = "land_area")
    private String landArea;

    // --- AMBIENTES ---
    private Integer rooms;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer garages;
    private Integer pax;

    // --- JSON CONVERTERS (Listas y Mapas simples) ---

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "text") // Usar 'text' en Postgres es seguro y compatible
    private List<String> amenities = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "text")
    private List<String> tags = new ArrayList<>();

    @Convert(converter = StringMapConverter.class)
    @Column(name = "other_areas", columnDefinition = "text")
    private Map<String, String> otherAreas = new HashMap<>();

    @Convert(converter = StringMapConverter.class)
    @Column(columnDefinition = "text")
    private Map<String, String> details = new HashMap<>();

    // --- RELACIONES SATÉLITE (1:1) ---

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PropertyMap map;

    
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch =
    FetchType.LAZY)
    private PropertyInternalData internalData;

    // --- RELACIONES MULTIMEDIA Y OTROS (1:N) ---

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyVideo> videos = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyOffer> offers = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyPublication> publications = new ArrayList<>();

    // OWNERS RELATIONSHIP (M:N)
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "property_owner_link", joinColumns = @JoinColumn(name = "property_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private List<Owner> owners = new ArrayList<>();

    // PRODUCER RELATIONSHIP
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id") 
    @ToString.Exclude
    private Producer producer;

    @ManyToMany(mappedBy = "properties", fetch = FetchType.LAZY) // "properties" es el nombre de la variable en Development
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Development> developments = new ArrayList<>();
}