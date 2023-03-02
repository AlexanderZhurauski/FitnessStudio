package org.mycompany.fitness.service.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;

public class StringToInstantConverter implements Converter<String, Instant> {

    @Override
    public Instant convert(String source) {
        return Instant.ofEpochMilli(Long.parseLong(source));
    }
}
