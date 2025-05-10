package com.fiap.soat.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DateUtil {
  public static LocalDateTime toDateTime(String dateTime) {
    if (StringUtils.isBlank(dateTime)) return null;
    return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
  }
}
