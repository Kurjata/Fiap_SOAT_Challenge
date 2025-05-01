package com.fiap.soat.model.request.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fiap.soat.constants.ControllerExceptions.GENERIC_FIELD_REQUIRED;
import static com.fiap.soat.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_PRODUCT_QUANTITY_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.INTEGER_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddProductRequest {
  @NotBlank(message = GENERIC_FIELD_REQUIRED)
  @Schema(description = ORDER_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String orderId;

  @NotBlank(message = GENERIC_FIELD_REQUIRED)
  @Schema(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String productId;

  @NotNull(message = GENERIC_FIELD_REQUIRED)
  @Schema(description = ORDER_PRODUCT_QUANTITY_DESCRIPTION, example = INTEGER_EXAMPLE)
  private Integer quantity;
}
