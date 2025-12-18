package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "development_internal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentInternalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal_observations", columnDefinition = "TEXT")
    private String internalObservations;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "development_id")
    @ToString.Exclude
    private Development development;
}