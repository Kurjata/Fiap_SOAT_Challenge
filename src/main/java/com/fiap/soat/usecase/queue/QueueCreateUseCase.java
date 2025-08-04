package com.fiap.soat.usecase.queue;

import com.fiap.soat.usecase.UseCase;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class QueueCreateUseCase implements UseCase<OrderDTO, QueueDTO> {
  private final QueueDatabaseGateway gateway;

  @Override
  public Mono<QueueDTO> execute(OrderDTO order) {
    return gateway.create(order);
  }
}
