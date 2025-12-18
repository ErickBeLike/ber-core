package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "development_maps")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "google_address")
    private String googleAddress;

    @Column(name = "google_map_link", columnDefinition = "TEXT")
    private String googleMapLink;

    private String latitude;
    private String longitude;
    
    @Column(name = "raw_coordinates")
    private String rawCoordinates;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "development_id")
    @ToString.Exclude
    private Development development;
}