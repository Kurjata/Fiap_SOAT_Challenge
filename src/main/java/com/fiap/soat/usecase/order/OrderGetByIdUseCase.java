package com.fiap.soat.usecase.order;

import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.usecase.UseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.ServiceError.ORDER_NOT_EXISTS;

@AllArgsConstructor
public class OrderGetByIdUseCase implements UseCase<String, OrderDTO> {
  private final OrderDatabaseGateway gateway;

  @Override
  public Mono<OrderDTO> execute(String id) {
    return gateway.findById(id).switchIfEmpty(Mono.error(new NotFoundException(ORDER_NOT_EXISTS)));
  }
}
