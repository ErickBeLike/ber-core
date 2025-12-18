package com.berraz.crm.core.property.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyOfferDTO {
    private String operationType; // VENTA, ALQUILER
    private String currency;      // U$S
    private BigDecimal price;
    private String status;  
    
    private List<String> interestedLeadNames;// ACTIVO, INACTIVO
}