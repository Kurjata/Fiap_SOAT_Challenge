package com.fiap.soat.usecase.order;

import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.enums.OrderStatus;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class OrderSetStatusUseCase implements UseCase<Pair<OrderStatus, OrderDTO>, OrderDTO> {
  private final OrderDatabaseGateway gateway;

  @Override
  public Mono<OrderDTO> execute(Pair<OrderStatus, OrderDTO> pair) {
    return Mono.just(pair.getRight())
        .map(order -> setStatus(order, pair.getLeft()))
        .flatMap(gateway::save);
  }

  private OrderDTO setStatus(OrderDTO order, OrderStatus status) {
    order.setStatus(status);
    return order;
  }
}
