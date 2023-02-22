package org.mycompany.fitness.dao.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

@Converter
public class InstantToLongConverter implements AttributeConverter<Instant, Long> {

    @Override
    public Long convertToDatabaseColumn(Instant instant) {
        return (instant != null) ? instant.toEpochMilli() : null;
    }

    @Override
    public Instant convertToEntityAttribute(Long epochMilli) {
        return (epochMilli != null) ? Instant.ofEpochMilli(epochMilli) : null;
    }
}
