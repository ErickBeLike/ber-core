package com.berraz.crm.core.property.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "property_maps")
@Data
public class PropertyMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "google_address")
    private String googleAddress;

    @Column(name = "google_map_link")
    private String googleMapLink;

    private Double latitude;
    private Double longitude;

    @Column(name = "waze_link")
    private String wazeLink;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @ToString.Exclude
    private Property property;
}
