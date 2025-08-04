package com.fiap.soat.api.external.mercadopago;

import static java.time.Duration.ofSeconds;

import com.fiap.soat.entities.integration.mercadopago.IntegrationRequest;
import com.fiap.soat.entities.integration.mercadopago.request.order.MercadoPagoOrderRequest;
import com.fiap.soat.entities.integration.mercadopago.request.qrcode.MercadoPagoQrcodeRequest;
import com.fiap.soat.entities.integration.mercadopago.response.order.MercadoPagoOrderResponse;
import com.fiap.soat.entities.integration.mercadopago.response.qrcode.MercadoPagoQrcodeResponse;
import com.fiap.soat.gateways.integration.mercadopago.MercadoPagoIntegrationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class MercadoPagoIntegration extends IntegrationBase
    implements MercadoPagoIntegrationGateway {
  @Value("${app.integration.mercado-pago.token}")
  private String token;

  @Value("${app.integration.mercado-pago.base-url}")
  private String baseUrl;

  @Value("${app.integration.mercado-pago.user-id}")
  private String userId;

  @Value("${app.integration.mercado-pago.merchant.external-id}")
  private String merchantExternalId;

  @Value("${app.integration.mercado-pago.order.create}")
  private String orderCreate;

  @Value("${app.integration.mercado-pago.order.get}")
  private String orderGet;

  @Value("${app.integration.mercado-pago.qrcode.create}")
  private String qrcodeCreate;

  @Override
  public Mono<MercadoPagoOrderResponse> createOrder(
      IntegrationRequest<MercadoPagoOrderRequest> request) {

    var uri = UriComponentsBuilder.fromUriString(baseUrl).path(orderCreate).toUriString();

    return getWebClient()
        .post()
        .uri(uri)
        .headers(headers -> fillHeaders(request, headers))
        .bodyValue(request.getBody())
        .retrieve()
        .bodyToMono(MercadoPagoOrderResponse.class)
        .timeout(ofSeconds(this.timeout))
        .retryWhen(this.retry());
  }

  @Override
  public Mono<MercadoPagoQrcodeResponse> createQrcode(
      IntegrationRequest<MercadoPagoQrcodeRequest> request) {
    var uri =
        UriComponentsBuilder.fromUriString(baseUrl)
            .path(qrcodeCreate)
            .uriVariables(fillCreateQrcodePath())
            .toUriString();

    return getWebClient()
        .post()
        .uri(uri)
        .headers(headers -> fillHeaders(request, headers))
        .bodyValue(request.getBody())
        .retrieve()
        .bodyToMono(MercadoPagoQrcodeResponse.class)
        .timeout(ofSeconds(this.timeout))
        .retryWhen(this.retry());
  }

  private HashMap<String, Object> fillCreateQrcodePath() {
    var path = new HashMap<String, Object>();
    path.put("userId", userId);
    path.put("merchant", merchantExternalId);
    return path;
  }

  private void fillHeaders(IntegrationRequest<?> request, HttpHeaders headers) {
    headers.setBearerAuth(token);
    headers.addAll(request.getHeaders());
  }
}
