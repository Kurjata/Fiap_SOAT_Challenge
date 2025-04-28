package com.fiap.soat.model.enums;

import lombok.Getter;

import java.util.Arrays;

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
