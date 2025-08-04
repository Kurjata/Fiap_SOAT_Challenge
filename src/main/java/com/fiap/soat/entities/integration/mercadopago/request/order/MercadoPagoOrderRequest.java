package com.fiap.soat.entities.integration.mercadopago.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderRequest {
  @JsonProperty("total_amount")
  private BigDecimal totalAmount;
  private MercadoPagoOrderConfigRequest config;
  private String type;
  private MercadoPagoOrderTransactions transactions;

  @JsonProperty("external_reference")
  private String soatOrderId;
}
