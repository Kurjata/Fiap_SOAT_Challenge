package com.fiap.soat.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddProductDTO {

  private String orderId;

  private String productId;

  private Integer quantity;
}
