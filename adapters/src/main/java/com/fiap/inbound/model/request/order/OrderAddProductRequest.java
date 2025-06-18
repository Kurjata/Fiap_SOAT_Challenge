package com.fiap.inbound.model.request.order;

import static com.fiap.soat.constants.ControllerExceptions.ORDER_ID_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.ORDER_ADD_PRODUCT_PRODUCT_ID_REQUIRED;
import static com.fiap.soat.constants.ControllerExceptions.ORDER_ADD_PRODUCT_QUANTITY_MIN_ONE;
import static com.fiap.soat.constants.ControllerExceptions.ORDER_ADD_PRODUCT_QUANTITY_REQUIRED;
import static com.fiap.soat.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_PRODUCT_QUANTITY_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.INTEGER_EXAMPLE;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddProductRequest {
  @NotBlank(message = ORDER_ID_REQUIRED)
  @Schema(description = ORDER_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String orderId;

  @NotBlank(message = ORDER_ADD_PRODUCT_PRODUCT_ID_REQUIRED)
  @Schema(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String productId;

  @NotNull(message = ORDER_ADD_PRODUCT_QUANTITY_REQUIRED)
  @Min(value = 1, message = ORDER_ADD_PRODUCT_QUANTITY_MIN_ONE)
  @Schema(description = ORDER_PRODUCT_QUANTITY_DESCRIPTION, example = INTEGER_EXAMPLE)
  private Integer quantity;
}
