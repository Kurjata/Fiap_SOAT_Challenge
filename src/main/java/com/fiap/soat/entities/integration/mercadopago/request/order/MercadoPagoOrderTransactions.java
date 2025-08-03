package com.fiap.soat.entities.integration.mercadopago.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderTransactions {
  private List<MercadoPagoOrderTransactionsPayment> payments;
}
