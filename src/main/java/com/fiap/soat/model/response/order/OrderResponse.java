package com.fiap.soat.model.response.order;

import static com.fiap.soat.constants.Description.ORDER_ID;
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
  @Schema(description = ORDER_ID, example = ID_EXAMPLE)
  private String id;

  private LocalDateTime timestampCreatedDate;

  private OrderCustomerResponse customer;

  private BigDecimal totalAmount;

  private OrderStatus status;

  private List<OrderProductResponse> products = new ArrayList<>();
}
