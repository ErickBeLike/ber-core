package com.berraz.crm.core.common.util.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null) {
            return "[]"; // Guardamos array vacío en DB si es null
        }
        return mapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(dbData, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            // Fallback por si la DB tiene basura o un formato inválido
            return Collections.emptyList();
        }
    }
}