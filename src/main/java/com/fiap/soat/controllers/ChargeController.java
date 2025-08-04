package com.fiap.soat.controllers;

import com.fiap.soat.entities.dto.charge.ChargeDTO;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import com.fiap.soat.gateways.integration.mercadopago.MercadoPagoIntegrationGateway;
import com.fiap.soat.usecase.charge.ChargeCreateUseCase;
import com.fiap.soat.usecase.charge.ChargePaymentUseCase;
import com.fiap.soat.usecase.order.OrderGetByIdUseCase;
import com.fiap.soat.usecase.order.OrderSetStatusUseCase;
import com.fiap.soat.usecase.queue.QueueCreateUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChargeController {
  private final ChargeCreateUseCase chargeCreateUseCase;
  private final ChargePaymentUseCase chargePaymentUseCase;

  public ChargeController(
      OrderDatabaseGateway orderGateway,
      QueueDatabaseGateway queueGateway,
      MercadoPagoIntegrationGateway mercadoPagoIntegrationGateway) {
    var orderGetByIdUseCase = new OrderGetByIdUseCase(orderGateway);
    var orderSetStatusUseCase = new OrderSetStatusUseCase(orderGateway);
    var queueCreateUseCase = new QueueCreateUseCase(queueGateway);
    this.chargeCreateUseCase =
        new ChargeCreateUseCase(
            orderGetByIdUseCase, orderSetStatusUseCase, mercadoPagoIntegrationGateway);
    this.chargePaymentUseCase =
        new ChargePaymentUseCase(orderGetByIdUseCase, orderSetStatusUseCase, queueCreateUseCase);
  }

  public Mono<ChargeDTO> create(ChargeDTO charge) {
    return chargeCreateUseCase.execute(charge);
  }

  public Mono<ChargeDTO> payment(ChargeDTO charge) {
    return chargePaymentUseCase.execute(charge);
  }
}
