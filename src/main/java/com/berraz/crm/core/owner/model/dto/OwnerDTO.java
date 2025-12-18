package com.berraz.crm.core.owner.model.dto;

import com.berraz.crm.core.property.model.dto.PropertySummaryDTO;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OwnerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String profileUrl;
    private Boolean privacy;

    // Lista ligera para ver qué tiene en cartera sin traernos toda la base de datos
    private List<PropertySummaryDTO> properties;

    // Auditoría básica (Opcional, pero recomendada para saber cuándo se dio de
    // alta)
    private LocalDateTime createdAt;
    private String createdBy;
}
