package com.berraz.crm.core.property.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PropertyPublicationDTO {
    private String portalName;       // ZonaProp, ArgenProp
    private String publicationState; // Publicado, Pausado
    private String promotionPlan;    // Destacado, Super
    private String listingUrl;       // Link al aviso
    private LocalDateTime lastPublishedAt;
    
    // Snapshot del precio al momento de publicar
    private String operationType;
    private String operationCurrency;
    private BigDecimal operationPrice;
}