package com.berraz.crm.core.development.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * DTO Maestro que agrupa toda la información de un Emprendimiento.
 * Utiliza clases internas estáticas (Inner Classes) para mantener el orden.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentDTO {

    // --- DATOS PRINCIPALES ---
    private Long id;
    private String tivId;              // ID del CRM
    private String name;
    private String url;
    private String category;           // Barrio Cerrado / Edificio
    private String constructionPhase;  // Terminado / Pozo
    private String location;
    private String status;             // Activo / Inactivo
    private String possessionDate;
    
    // --- DESCRIPCIONES ---
    private String observations;       // Texto corto
    private String webDescription;     // Texto largo (HTML/Markdown)

    // --- JSONB (Detalles Dinámicos) ---
    // Se mapea directamente a un Map de Java para que el frontend reciba un JSON nativo
    private Map<String, Object> details;

    // --- MULTIMEDIA (Listas Simples) ---
    // Enviamos solo las URLs para facilitar la visualización rápida
    private List<String> images;
    private List<String> videos;
    private List<String> plans;

    // --- SUB-OBJETOS ESTRUCTURADOS (Inner DTOs) ---
    
    private MapDataDTO map;                   // Datos del Mapa
    private InternalDataDTO internalData;     // Observaciones privadas
    private AuditDataDTO auditData;           // Datos del Productor (Link)
    
    // Lista de Publicaciones (Inner DTO)
    private List<PublicationDTO> publications; 


    // ========================================================================
    // CLASES INTERNAS (Inner DTOs)
    // ========================================================================

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MapDataDTO {
        private String googleAddress;
        private String googleMapLink;
        private String latitude;
        private String longitude;
        private String rawCoordinates;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InternalDataDTO {
        private String internalObservations;
        // Aquí podrías agregar más campos internos si vuelven a nacer en el futuro
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuditDataDTO {
        // Mapeo de la tabla 'development_producer_link'
        private String createdBy;
        private LocalDateTime createdAt;
        private String updatedBy;
        private LocalDateTime updatedAt;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PublicationDTO {
        // Mapeo de la tabla 'development_publications'
        private Long id;
        private String portalName;       // ZonaProp, ArgenProp...
        private String listingUrl;       // Link al aviso real
        private String promotionPlan;    // Super Destacado...
        private String status;           // Online / Pausado
        private String statusDetail;     // Razón de la baja
        private LocalDateTime publishedAt;
        private LocalDateTime finishedAt;
    }
}