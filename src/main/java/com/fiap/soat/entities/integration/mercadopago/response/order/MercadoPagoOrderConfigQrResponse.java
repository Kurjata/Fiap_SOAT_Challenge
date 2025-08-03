package com.fiap.soat.entities.integration.mercadopago.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderConfigQrResponse {
  @JsonProperty("external_pos_id")
  private String merchant;

  private String mode;
}
