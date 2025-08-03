package com.fiap.soat.entities.integration.mercadopago.response.order;

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
public class MercadoPagoOrderResponse {
  private String id;
  private String type;
  private String processingMode;

  @JsonProperty("external_reference")
  private String soatOrderId;

  private BigDecimal totalAmount;
  private String expirationTime;
  private String countryCode;
  private Long userId;
  private String status;
  private String statusDetails;
  private String currency;
  private String createdDate;
  private String lastUpdatedDate;
  private MercadoPagoOrderIntegrationDataResponse integrationData;
  private MercadoPagoOrderTransactionsResponse transactions;
}
