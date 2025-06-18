package com.fiap.inbound.model.request.product;

import com.fiap.soat.model.enums.ProductCategory;
import com.fiap.soat.rest.validation.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.fiap.soat.constants.ControllerExceptions.PRODUCT_CREATE_AMOUNT_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.PRODUCT_CREATE_CATEGORY_INVALID;
import static com.fiap.soat.constants.ControllerExceptions.PRODUCT_CREATE_CATEGORY_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.PRODUCT_CREATE_NAME_REQUIRED;
import static com.fiap.soat.constants.Description.PRODUCT_AMOUNT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_CATEGORY_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Example.AMOUNT_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_DESCRIPTION_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_NAME_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
  @Schema(description = PRODUCT_NAME_DESCRIPTION, example = PRODUCT_NAME_EXAMPLE)
  @NotBlank(message = PRODUCT_CREATE_NAME_REQUIRED)
  private String name;

  @Schema(description = PRODUCT_CATEGORY_DESCRIPTION, implementation = ProductCategory.class)
  @ValueOfEnum(enumClass = ProductCategory.class, message = PRODUCT_CREATE_CATEGORY_INVALID)
  @NotBlank(message = PRODUCT_CREATE_CATEGORY_REQUIRED)
  private String category;

  @Schema(description = PRODUCT_AMOUNT_DESCRIPTION, example = AMOUNT_EXAMPLE)
  @NotNull(message = PRODUCT_CREATE_AMOUNT_REQUIRED)
  private BigDecimal amount;

  @Schema(description = PRODUCT_DESCRIPTION, example = PRODUCT_DESCRIPTION_EXAMPLE)
  private String description;
}
