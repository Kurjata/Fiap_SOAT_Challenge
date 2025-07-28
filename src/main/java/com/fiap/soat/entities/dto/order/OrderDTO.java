package com.fiap.soat.entities.dto.order;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fiap.soat.entities.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fiap.soat.entities.enums.OrderStatus.CREATED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private OrderCustomerDTO customer;

  @Builder.Default private OrderStatus status = CREATED;

  @Builder.Default private List<OrderProductDTO> products = new ArrayList<>();

  public boolean existsProductInList(String id) {
    return products.stream().anyMatch(product -> product.getId().equals(id));
  }

  public void removeProductInList(String id) {
    this.products =
        products.stream().filter(Predicate.not(product -> product.getId().equals(id))).toList();
  }

  public BigDecimal getTotalAmount() {
    return products.stream()
        .map(OrderProductDTO::getTotalAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
