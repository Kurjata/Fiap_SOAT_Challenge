package com.fiap.soat.rest.validation.impl;

import com.fiap.soat.rest.validation.ValueOfEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ValueOfEnumImpl implements ConstraintValidator<ValueOfEnum, String> {
  List<String> values;
  List<String> excluded;

  @Override
  public void initialize(ValueOfEnum constraint) {
    this.values = Arrays.stream(constraint.enumClass().getEnumConstants()).map(Enum::name).toList();
    this.excluded = Arrays.asList(constraint.excluded());
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return StringUtils.isBlank(value)
        || this.values.stream().filter(s -> !excluded.contains(s)).anyMatch(s -> s.equals(value));
  }
}
