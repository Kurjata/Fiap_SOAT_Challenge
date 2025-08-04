package com.fiap.soat.entities.integration.mercadopago.request.qrcode;

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
public class MercadoPagoQrcodeItemRequest {
  private String category;
  private Integer quantity;

  @JsonProperty("unit_price")
  private BigDecimal amount;

  @JsonProperty("sku_number")
  private String productId;

  @JsonProperty("title")
  private String productName;

  @JsonProperty("description")
  private String productDescription;

  @JsonProperty("total_amount")
  private BigDecimal totalAmount;

  @JsonProperty("unit_measure")
  private String unitMeasure;
}
