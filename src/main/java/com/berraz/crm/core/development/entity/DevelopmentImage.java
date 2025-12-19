package com.berraz.crm.core.development.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "development_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevelopmentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "development_id")
    @ToString.Exclude
    private Development development;
}
