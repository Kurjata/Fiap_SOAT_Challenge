package com.fiap.enums;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ProductCategory {
  SNACK,
  SIDE_DISH,
  DRINK,
  DESSERT;

  public static ProductCategory getByName(String name) {
    return Arrays.stream(ProductCategory.values())
        .filter(p -> p.name().equals(name))
        .findFirst()
        .orElse(null);
  }
}
