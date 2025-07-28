package com.fiap.soat.usecase.queue;

import com.fiap.soat.usecase.UseCase;

import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.enums.QueueTrackingStatus;
import com.fiap.soat.entities.exception.BusinessException;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.fiap.soat.entities.enums.QueueTrackingStatus.FINISHED;
import static com.fiap.soat.entities.enums.QueueTrackingStatus.IN_PREPARATION;
import static com.fiap.soat.entities.enums.QueueTrackingStatus.READY;
import static com.fiap.soat.entities.enums.QueueTrackingStatus.RECEIVED;
import static com.fiap.soat.entities.enums.ServiceError.QUEUE_ENVOLVE_STATUS_FINISHED;
import static com.fiap.soat.entities.enums.ServiceError.QUEUE_NOT_FIRST_PROCESS;


@AllArgsConstructor
public class QueueNextStatusUseCase implements UseCase<String, QueueDTO> {
  private final QueueDatabaseGateway gateway;
  private final UseCase<String, QueueDTO> queueGetByIdUseCase;

  @Override
  public Mono<QueueDTO> execute(String id) {
    return queueGetByIdUseCase
        .execute(id)
        .flatMap(this::envolveReceived)
        .flatMap(this::envolveInPreparation)
        .flatMap(this::envolveReady)
        .flatMap(this::envolveFinished)
        .flatMap(gateway::save);
  }

  private Mono<QueueDTO> envolveReceived(QueueDTO queue) {
    return Mono.just(queue.getStatus())
        .filter(RECEIVED::equals)
        .flatMap(
            status ->
                gateway
                    .findFirstReceived()
                    .filter(received -> received.getId().equals(queue.getId()))
                    .map(d -> setStatus(d, IN_PREPARATION))
                    .switchIfEmpty(
                        Mono.error(new BusinessException(QUEUE_NOT_FIRST_PROCESS))))
        .defaultIfEmpty(queue);
  }

  private Mono<QueueDTO> envolveInPreparation(QueueDTO queue) {
    return Mono.just(queue.getStatus())
        .filter(IN_PREPARATION::equals)
        .map(status -> setStatus(queue, READY))
        .defaultIfEmpty(queue);
  }

  private Mono<QueueDTO> envolveReady(QueueDTO queue) {
    return Mono.just(queue.getStatus())
        .filter(READY::equals)
        .map(status -> setStatus(queue, FINISHED))
        .defaultIfEmpty(queue);
  }

  private Mono<QueueDTO> envolveFinished(QueueDTO queue) {
    return Mono.just(queue.getStatus())
        .filter(FINISHED::equals)
        .flatMap(q -> Mono.error(new BusinessException(QUEUE_ENVOLVE_STATUS_FINISHED)))
        .defaultIfEmpty(queue)
        .cast(QueueDTO.class);
  }

  private QueueDTO setStatus(QueueDTO queue, QueueTrackingStatus status) {
    queue.setStatus(status);
    queue.setTimestampCurrentStatus(LocalDateTime.now());
    return queue;
  }
}
