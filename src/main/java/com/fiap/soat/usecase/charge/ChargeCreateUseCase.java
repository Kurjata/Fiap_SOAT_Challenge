package com.fiap.soat.usecase.charge;

import static com.fiap.soat.entities.enums.OrderStatus.CREATED;
import static com.fiap.soat.entities.enums.OrderStatus.WAITING_FOR_PAYMENT;
import static com.fiap.soat.entities.enums.ServiceError.CHARGE_ORDER_PRODUCTS_EMPTY;
import static com.fiap.soat.entities.enums.ServiceError.CHARGE_ORDER_STATUS_NOT_CREATED;

import com.fiap.soat.entities.dto.charge.ChargeDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.enums.OrderStatus;
import com.fiap.soat.entities.exception.BusinessException;
import java.util.function.Predicate;

import com.fiap.soat.gateways.integration.mercadopago.MercadoPagoIntegrationGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ChargeCreateUseCase implements UseCase<ChargeDTO, ChargeDTO> {
  private final UseCase<String, OrderDTO> orderGetByIdUseCase;
  private final UseCase<Pair<OrderStatus, OrderDTO>, OrderDTO> OrderSetStatusUseCase;
  private final MercadoPagoIntegrationGateway mercadoPagoIntegration;

  @Override
  public Mono<ChargeDTO> execute(ChargeDTO charge) {
    return getOrderById(charge.getOrderId())
        .flatMap(order -> OrderSetStatusUseCase.execute(Pair.of(WAITING_FOR_PAYMENT, order)))
        // verificar lógica quando tiver integração com mercado pago
        .thenReturn(charge);
  }

  private Mono<OrderDTO> getOrderById(String orderId) {
    return orderGetByIdUseCase
        .execute(orderId)
        .filter(order -> CREATED.equals(order.getStatus()))
        .switchIfEmpty(Mono.error(new BusinessException(CHARGE_ORDER_STATUS_NOT_CREATED)))
        .filter(Predicate.not(order -> order.getProducts().isEmpty()))
        .switchIfEmpty(Mono.error(new BusinessException(CHARGE_ORDER_PRODUCTS_EMPTY)));
  }
}
