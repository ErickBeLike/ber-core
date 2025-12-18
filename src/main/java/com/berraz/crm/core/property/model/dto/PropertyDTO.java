package com.berraz.crm.core.property.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.berraz.crm.core.lead.model.dto.ProducerDTO;
import com.berraz.crm.core.owner.model.dto.OwnerDTO;

@Data
public class PropertyDTO {

    // --- IDENTIFICADORES ---
    private Long id;
    private String code;

    // --- INFO PRINCIPAL ---
    private String title;
    private String description;
    private String referenceText;
    private String type; // Casa, Lote
    private String status; // ACTIVE, INACTIVE
    private String location; // Texto de ubicación (ej: "Pilar, BsAs")
    private String zoning;

    // --- ECONÓMICO ---
    private String operationType; // Venta, Alquiler
    private String currency;
    private BigDecimal price;
    private String expenses;

    // --- CARACTERÍSTICAS FÍSICAS ---
    private String coveredArea;
    private String totalArea;
    private String landArea;
    private Integer rooms;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer garages;
    private Integer pax;
    private String antiquity;
    private String conditionState;
    private String orientation;

    // --- LISTAS Y MAPAS (Valores planos) ---
    private List<String> amenities;
    private List<String> tags;
    private Map<String, String> details;
    private Map<String, String> otherAreas;

    // --- MULTIMEDIA (URLs simples para v1) ---
    private List<String> imageUrls;
    private List<String> planUrls;
    private List<String> videoUrls;

    // --- DATOS INTERNOS & MIGRACIÓN (Lo Nuevo) ---
    // El Timeline de sucesos (Chat/Bitácora)
    private PropertyInternalDataDTO internalData;

    // --- UBICACIÓN GEO (DTO Satélite) ---
    private PropertyLocationDTO mapLocation;

    // --- HISTORIAL COMERCIAL (DTOs Satélite) ---
    private List<PropertyOfferDTO> offers;
    private List<PropertyPublicationDTO> publications;

    // --- PRODUCER ---
    private ProducerDTO producer;

    // --- OWNERS ---
    private List<OwnerDTO> owners;

    // --- DATE CREATED / UPDATED ---
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}