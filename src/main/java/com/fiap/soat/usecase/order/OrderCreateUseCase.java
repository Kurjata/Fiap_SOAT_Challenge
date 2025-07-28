package com.fiap.soat.usecase.order;

import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class OrderCreateUseCase implements UseCase<OrderDTO, OrderDTO> {
  private final OrderDatabaseGateway gateway;

  @Override
  public Mono<OrderDTO> execute(OrderDTO order) {
    return gateway.save(order);
  }
}
