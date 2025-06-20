package com.fiap.dto.order;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
  private String id;
  private BigDecimal amount;
  private Integer quantity;
  private String name;
  private String description;

  public BigDecimal getTotalAmount() {
    return amount.multiply(BigDecimal.valueOf(quantity));
  }
}
