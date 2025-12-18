package com.berraz.crm.core.lead.model.dto;

import lombok.Data;

@Data
public class ProducerDTO {
    private Long id;
    private String name;
    private String officeContact;
    private String email;
    private String phone;
    
    // No ponemos listas de propiedades/leads aquí para mantenerlo ligero.
    // Si necesitas ver su cartera, harás un endpoint específico.
}