package com.berraz.crm.core.property.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_internal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyInternalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal_observations", columnDefinition = "TEXT")
    private String internalObservations;

    @Column(name = "office_observations", columnDefinition = "TEXT")
    private String officeObservations;

    @Column(name = "conditions", columnDefinition = "TEXT")
    private String conditions;

    // --- RELACIÓN ---
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @ToString.Exclude
    private Property property;

    public void setProperty(Property property) {
        this.property = property;
    }
}