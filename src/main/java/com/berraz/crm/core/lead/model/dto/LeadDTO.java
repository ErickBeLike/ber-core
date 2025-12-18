package com.berraz.crm.core.lead.model.dto;

import com.berraz.crm.core.property.model.dto.PropertyOfferDTO;
import lombok.Data;
import java.util.List;

@Data
public class LeadDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String clientState;
    private Boolean isPrivate;
    private ProducerDTO producer;
    
    // Lista directa de ofertas por las que preguntó
    private List<PropertyOfferDTO> interestedOffers;
}