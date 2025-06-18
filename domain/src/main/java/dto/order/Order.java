package dto.order;

import com.fiap.soat.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.fiap.soat.model.enums.OrderStatus.CREATED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private OrderCustomer customer;

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
