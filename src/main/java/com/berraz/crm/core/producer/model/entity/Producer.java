package com.berraz.crm.core.producer.model.entity;

import com.berraz.crm.core.common.model.AuditableEntity;
import com.berraz.crm.core.development.entity.Development;
import com.berraz.crm.core.lead.model.entity.Lead;
import com.berraz.crm.core.property.model.entity.Property;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Producer extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID original de TIV (importante porque en los CSVs viejos usas este ID)
    // El Producer ID 54 en el CSV debe ser el Producer ID 54 aquí.
    // Tip: En la migración, fuerza el ID al guardar o usa un campo "legacy_id".
    private Long legacyId;

    @Column(nullable = false)
    private String name;

    // En tu CSV viene un string largo: "(11) 5180... - info@..."
    // Guárdalo así por ahora, luego con un script lo limpias.
    @Column(name = "office_contact")
    private String officeContact;

    private String email; // Para llenar después
    private String phone; // Para llenar después

    // --- SU CARTERA DE PROPIEDADES (1:N) ---
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Property> assignedProperties = new ArrayList<>();

    // --- SUS CLIENTES ASIGNADOS (1:N) ---
    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Lead> assignedLeads = new ArrayList<>();

    // ========================================================================
    // NUEVA RELACIÓN MUCHOS A MUCHOS CON DEVELOPMENTS
    // ========================================================================
    // mappedBy apunta al nombre del CAMPO Java en la clase Development ("assignedProducers")
    @ManyToMany(mappedBy = "assignedProducers", fetch = FetchType.LAZY)
    @ToString.Exclude // Evita StackOverflowError
    @EqualsAndHashCode.Exclude
    private List<Development> developments = new ArrayList<>();
}