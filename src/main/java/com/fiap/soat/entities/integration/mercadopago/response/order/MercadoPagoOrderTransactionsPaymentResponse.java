package com.fiap.soat.entities.integration.mercadopago.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderTransactionsPaymentResponse {
  private String id;
  private BigDecimal amount;
  private String status;
  private String statusDetails;
}
