package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "development_producer_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentProducerLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "development_id")
    @ToString.Exclude
    private Development development;
}