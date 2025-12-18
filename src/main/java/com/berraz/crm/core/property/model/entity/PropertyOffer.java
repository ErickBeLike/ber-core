package com.berraz.crm.core.property.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.berraz.crm.core.lead.model.entity.Lead;

@Entity
@Table(name = "property_offers")
@Data
public class PropertyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_type")
    private String operationType; // VENTA, ALQUILER

    private String currency; // U$S
    private BigDecimal price;
    
    private String status; // ACTIVO, INACTIVO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @ToString.Exclude
    private Property property;

    @ManyToMany(mappedBy = "interestedOffers", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Lead> interestedLeads = new ArrayList<>();
}
