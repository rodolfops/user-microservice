package com.rodolfosaturnino.user.microservice.config;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, String> {
	@Override
	public String convert(ZonedDateTime value) {
		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value);
	}
}

