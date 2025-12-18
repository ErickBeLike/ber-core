package com.berraz.crm.core.lead.model.entity;

import com.berraz.crm.core.common.model.AuditableEntity;
import com.berraz.crm.core.property.model.entity.PropertyOffer;
import com.berraz.crm.core.producer.model.entity.Producer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lead extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String email;
    private String phone;

    @Column(name = "client_state")
    private String clientState;

    @Column(name = "is_private")
    private Boolean isPrivate;

    // --- ASIGNACIÓN ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_producer_id") // La columna física en la tabla leads
    @ToString.Exclude
    private Producer producer;

    // --- INTERESES (M:N SIMPLE) ---
    // Aquí guardamos las ofertas por las que preguntó.
    // Hibernate gestionará la tabla 'offer_lead_link' solo con IDs.
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "offer_lead_link", // Nombre exacto de tu tabla
            joinColumns = @JoinColumn(name = "lead_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<PropertyOffer> interestedOffers = new ArrayList<>();
}
