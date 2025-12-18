package com.berraz.crm.core.property.model.dto;

import lombok.Data;

@Data
public class PropertyLocationDTO {
    private String googleAddress;
    private String googleMapLink;
    private String wazeLink;
    private Double latitude;
    private Double longitude;
}