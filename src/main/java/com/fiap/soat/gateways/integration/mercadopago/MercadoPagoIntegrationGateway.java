package com.fiap.soat.gateways.integration.mercadopago;

import com.fiap.soat.entities.integration.mercadopago.IntegrationRequest;
import com.fiap.soat.entities.integration.mercadopago.request.order.MercadoPagoOrderRequest;
import com.fiap.soat.entities.integration.mercadopago.request.qrcode.MercadoPagoQrcodeRequest;
import com.fiap.soat.entities.integration.mercadopago.response.order.MercadoPagoOrderResponse;
import com.fiap.soat.entities.integration.mercadopago.response.qrcode.MercadoPagoQrcodeResponse;
import reactor.core.publisher.Mono;

public interface MercadoPagoIntegrationGateway {
  Mono<MercadoPagoOrderResponse> createOrder(IntegrationRequest<MercadoPagoOrderRequest> request);

  Mono<MercadoPagoQrcodeResponse> createQrcode(
      IntegrationRequest<MercadoPagoQrcodeRequest> request);
}
