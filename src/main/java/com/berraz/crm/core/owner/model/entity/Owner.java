package com.berraz.crm.core.owner.model.entity;

import com.berraz.crm.core.common.model.AuditableEntity;
import com.berraz.crm.core.property.model.entity.Property;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Owner extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phone;

    @Column(name = "profile_url")
    private String profileUrl;

    private Boolean privacy;

    // --- RELACIÓN INVERSA (M:N) ---
    // Un dueño tiene varias propiedades.
    // "mappedBy" indica que la configuración de la tabla intermedia está en la
    // clase Property.
    @ManyToMany(mappedBy = "owners", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Property> properties = new ArrayList<>();
}
