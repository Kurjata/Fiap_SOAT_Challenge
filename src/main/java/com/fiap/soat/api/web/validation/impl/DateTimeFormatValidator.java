package com.fiap.soat.api.web.validation.impl;


import com.fiap.soat.api.web.validation.DateTimeFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fiap.soat.entities.constants.Constants.DATE_PATTERN;
import static com.fiap.soat.entities.constants.Constants.DATE_TIME_PATTERN;


public class DateTimeFormatValidator implements ConstraintValidator<DateTimeFormat, String> {
  private boolean isDateTime;
  private String pattern;

  @Override
  public void initialize(DateTimeFormat constraint) {
    this.isDateTime = constraint.dateTime();
    this.pattern = constraint.pattern();
  }

  @Override
  public boolean isValid(String dateTime, ConstraintValidatorContext constraintValidatorContext) {
    if (StringUtils.isBlank(dateTime)) return true;

    try {
      LocalDate response;
      var date = dateTime.substring(0, 10);
      if (isDateTime) {
        if (StringUtils.isNotBlank(pattern))
          response =
              LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(this.pattern))
                  .toLocalDate();
        else
          response =
              LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
                  .toLocalDate();
      } else response = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(DATE_PATTERN));

      return String.valueOf(response).equals(date);

    } catch (Exception e) {
      return false;
    }
  }
}
