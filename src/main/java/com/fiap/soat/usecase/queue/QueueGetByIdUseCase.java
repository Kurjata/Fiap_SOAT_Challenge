package com.fiap.soat.usecase.queue;

import com.fiap.soat.usecase.UseCase;

import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.exception.NotFoundException;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static com.fiap.soat.entities.enums.ServiceError.QUEUE_NOT_FOUND;

@AllArgsConstructor
public class QueueGetByIdUseCase implements UseCase<String, QueueDTO> {
  private final QueueDatabaseGateway gateway;

  @Override
  public Mono<QueueDTO> execute(String id) {
    return gateway.findById(id).switchIfEmpty(Mono.error(new NotFoundException(QUEUE_NOT_FOUND)));
  }
}
