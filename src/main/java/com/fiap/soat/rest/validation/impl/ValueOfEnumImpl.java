package com.fiap.soat.rest.validation.impl;

import com.fiap.soat.rest.validation.ValueOfEnum;
import io.netty.util.internal.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ValueOfEnumImpl implements ConstraintValidator<ValueOfEnum, String> {
  List<String> values;

  @Override
  public void initialize(ValueOfEnum constraint) {
    this.values = Arrays.stream(constraint.enumClass().getEnumConstants()).map(Enum::name).toList();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return StringUtils.isBlank(value) || this.values.stream().anyMatch(s -> s.equals(value));
  }
}
