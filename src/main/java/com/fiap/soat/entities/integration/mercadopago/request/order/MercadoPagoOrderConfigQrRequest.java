package com.fiap.soat.entities.integration.mercadopago.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderConfigQrRequest {
  @JsonProperty("external_pos_id")
  private String merchantExternalId;
}
