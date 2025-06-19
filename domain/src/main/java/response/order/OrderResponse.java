package response.order;

import static com.fiap.soat.constants.Description.ORDER_CUSTOMER_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_PRODUCTS_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_STATUS_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_TIMESTAMP_CREATE_DESCRIPTION;
import static com.fiap.soat.constants.Description.ORDER_TOTAL_AMOUNT_DESCRIPTION;
import static com.fiap.soat.constants.Example.AMOUNT_EXAMPLE;
import static com.fiap.soat.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fiap.soat.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderResponse {
  @Schema(description = ORDER_ID_DESCRIPTION, example = ID_EXAMPLE)
  private String id;

  @Schema(description = ORDER_TIMESTAMP_CREATE_DESCRIPTION, example = DATE_TIME_EXAMPLE)
  private LocalDateTime timestampCreatedDate;

  @Schema(description = ORDER_CUSTOMER_DESCRIPTION)
  private OrderCustomerResponse customer;

  @Schema(description = ORDER_TOTAL_AMOUNT_DESCRIPTION, example = AMOUNT_EXAMPLE)
  private BigDecimal totalAmount;

  @Schema(description = ORDER_STATUS_DESCRIPTION, implementation = OrderStatus.class)
  private OrderStatus status;

  @Schema(description = ORDER_PRODUCTS_DESCRIPTION)
  private List<OrderProductResponse> products = new ArrayList<>();
}
