package pl.java.scalatech.domain.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.extern.slf4j.Slf4j;

//@Converter(autoApply = true)
@Slf4j
public class LocalDateTimeConvertor implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime date) {
        if (date != null) return Date.from(Instant.from(date));
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date value) {
        if (value != null) return LocalDateTime.from(value.toInstant());
        return null;
    }
}