package com.fiap.soat.model.request.order;

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
  @NotBlank private String orderId;

  @NotBlank private String productId;

  @NotNull private Integer quantity;
}
