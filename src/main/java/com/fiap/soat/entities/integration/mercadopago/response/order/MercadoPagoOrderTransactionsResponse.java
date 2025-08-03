package com.fiap.soat.entities.integration.mercadopago.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderTransactionsResponse {
  private List<MercadoPagoOrderTransactionsPaymentResponse> payments;
}
