package com.fiap.soat.entities.integration.mercadopago.request.qrcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoQrcodeCashOutRequest {
  @Builder.Default private BigDecimal amount = BigDecimal.ZERO;
}
