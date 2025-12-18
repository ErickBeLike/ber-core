package com.berraz.crm.core.property.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Map;
import lombok.Builder;

@Data
@Builder
public class PropertyInternalDataDTO {
    private LocalDate extractedDate;
    private String internalObservations;
    private String officeObservations;
    private String conditions;
    private Map<String, String> otherData;
}