package com.fiap.soat.model.response.order;

import static com.fiap.soat.constants.Description.PRODUCT_AMOUNT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.PRODUCT_NAME_DESCRIPTION;
import static com.fiap.soat.constants.Example.AMOUNT_EXAMPLE;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_DESCRIPTION_EXAMPLE;
import static com.fiap.soat.constants.Example.PRODUCT_NAME_EXAMPLE;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
  @Schema(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String id;

  @Schema(description = PRODUCT_AMOUNT_DESCRIPTION, example = AMOUNT_EXAMPLE)
  private BigDecimal amount;

  @Schema(description = PRODUCT_NAME_DESCRIPTION, example = PRODUCT_NAME_EXAMPLE)
  private String name;

  @Schema(description = PRODUCT_DESCRIPTION, example = PRODUCT_DESCRIPTION_EXAMPLE)
  private String description;
}
