package com.fiap.soat.entities.integration.mercadopago.request.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoQrcodeRequest {
  private String notificationUrl;
  private String title;
  private String totalAmount;

  @JsonProperty("external_reference")
  private String soatOrderId;

  private String description;

  @JsonProperty("cash_out")
  @Builder.Default
  private MercadoPagoQrcodeCashOutRequest cashOut =
      MercadoPagoQrcodeCashOutRequest.builder().build();

  @Builder.Default
  private List<MercadoPagoQrcodeItemRequest> items = new ArrayList<>();
}
