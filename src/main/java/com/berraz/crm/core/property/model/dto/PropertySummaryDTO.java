package com.berraz.crm.core.property.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PropertySummaryDTO {
    private Long id;
    private String code;
    private String title;
    private String location;
    
    // Datos comerciales básicos
    private String operationType;
    private String currency;
    private BigDecimal price;
    private String status;
    
    // Solo la URL de la primera foto para mostrar en la lista
    private String coverPhotoUrl; 
}