package com.fiap.soat.usecase.order;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.exception.BusinessException;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.OrderStatus.CANCELED;
import static com.fiap.soat.entities.enums.OrderStatus.CREATED;
import static com.fiap.soat.entities.enums.OrderStatus.PAID;
import static com.fiap.soat.entities.enums.OrderStatus.WAITING_FOR_PAYMENT;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_STATUS_IS_ALREADY_CANCELLED;
import static com.fiap.soat.entities.enums.ServiceError.ORDER_STATUS_PAID_NOT_CANCEL;

@AllArgsConstructor
public class OrderCancelUseCase implements UseCase<String, OrderDTO> {
  private final OrderDatabaseGateway gateway;
  private final UseCase<String, OrderDTO> orderGetByIdUseCase;

  @Override
  public Mono<OrderDTO> execute(String id) {
    return orderGetByIdUseCase
        .execute(id)
        .flatMap(this::routeCancel)
        .map(this::setCancelStatus)
        .flatMap(gateway::save);
  }

  private OrderDTO setCancelStatus(OrderDTO order) {
    order.setStatus(CANCELED);
    return order;
  }

  private Mono<OrderDTO> routeCancel(OrderDTO order) {
    return routeCreated(order)
        .flatMap(this::routeWaitingForPayment)
        .flatMap(this::routePaid)
        .flatMap(this::routeCanceled);
  }

  private Mono<OrderDTO> routeCreated(OrderDTO order) {
    return Mono.just(order.getStatus())
        .filter(CREATED::equals)
        .map(status -> order)
        .defaultIfEmpty(order);
  }

  private Mono<OrderDTO> routeWaitingForPayment(OrderDTO order) {
    // TODO: Verificar lógica quando tiver a integração com o mercado pago
    return Mono.just(order.getStatus())
        .filter(WAITING_FOR_PAYMENT::equals)
        .map(status -> order)
        .defaultIfEmpty(order);
  }

  private Mono<OrderDTO> routePaid(OrderDTO order) {
    return Mono.just(order.getStatus())
        .filter(PAID::equals)
        .flatMap(s -> Mono.error(new BusinessException(ORDER_STATUS_PAID_NOT_CANCEL)))
        .thenReturn(order);
  }

  private Mono<OrderDTO> routeCanceled(OrderDTO order) {
    return Mono.just(order.getStatus())
        .filter(CANCELED::equals)
        .flatMap(s -> Mono.error(new BusinessException(ORDER_STATUS_IS_ALREADY_CANCELLED)))
        .thenReturn(order);
  }
}
