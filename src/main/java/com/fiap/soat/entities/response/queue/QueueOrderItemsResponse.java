package com.fiap.soat.entities.response.queue;

import static com.fiap.soat.entities.constants.Example.INTEGER_EXAMPLE;
import static com.fiap.soat.entities.constants.Example.PRODUCT_NAME_EXAMPLE;
import static com.fiap.soat.entities.constants.Description.QUEUE_ITEMS_NAME_DESCRIPTION;
import static com.fiap.soat.entities.constants.Description.QUEUE_ITEMS_QUANTITY_DESCRIPTION;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueOrderItemsResponse {
  @Schema(description = QUEUE_ITEMS_NAME_DESCRIPTION, example = PRODUCT_NAME_EXAMPLE)
  private String name;

  @Schema(description = QUEUE_ITEMS_QUANTITY_DESCRIPTION, example = INTEGER_EXAMPLE)
  private Integer quantity;
}
