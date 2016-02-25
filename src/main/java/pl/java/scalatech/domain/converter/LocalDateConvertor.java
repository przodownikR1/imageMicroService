package pl.java.scalatech.domain.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConvertor implements AttributeConverter<LocalDate, Date> {

  @Override
  public Date convertToDatabaseColumn(LocalDate date) {
    return Date.from(Instant.from(date.atStartOfDay(ZoneId.systemDefault())));
  }

  @Override
  public LocalDate convertToEntityAttribute(Date value) {
    return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}