package com.fiap.soat.entities.integration.mercadopago.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderConfigRequest {
  private MercadoPagoOrderConfigQrRequest qr;
}
