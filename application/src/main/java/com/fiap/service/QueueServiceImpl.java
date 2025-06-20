package com.fiap.service;

import dto.order.Order;
import dto.queue.Queue;
import dto.queue.QueueHistory;
import enums.QueueTrackingStatus;
import enums.ServiceError;
import exception.BusinessException;
import exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import repository.QueueRepository;

import java.time.LocalDateTime;

import static enums.QueueTrackingStatus.FINISHED;
import static enums.QueueTrackingStatus.IN_PREPARATION;
import static enums.QueueTrackingStatus.READY;

@Service
@AllArgsConstructor
public class QueueServiceImpl {
  private QueueRepository queueRepository;

  public Mono<Queue> create(Order order) {
    return Mono.just(order)
        .flatMap(this.queueRepository::create)
        .map(this::addHistory)
        .flatMap(this.queueRepository::save);
  }

  public Mono<PageImpl<Queue>> getByFilter(QueueFilterDTO filter) {
    return this.queueRepository
        .getCountByFilter(filter)
        .flatMap(
            total ->
                this.queueRepository
                    .getByFilter(filter)
                    .map(this.queueMapper::toDTO)
                    .collectList()
                    .map(list -> new PageImpl<>(list, filter.getPageable(), total)));
  }

  public Mono<Queue> nextStatus(String id) {
    return Mono.just(id)
        .filter(ObjectId::isValid)
        .map(ObjectId::new)
        .flatMap(this.queueRepository::findById)
        .flatMap(this::envolveStatus)
        .map(this::addHistory)
        .flatMap(this.queueRepository::save)
        .switchIfEmpty(Mono.error(new NotFoundException(ServiceError.QUEUE_NOT_FOUND)));
  }

  private Queue addHistory(Queue queue) {
    queue
        .getHistory()
        .add(
            QueueHistory.builder()
                .status(queue.getStatus())
                .timestamp(queue.getTimestampCurrentStatus())
                .build());
    return queue;
  }

  private Mono<Queue> envolveStatus(Queue document) {
    return switch (document.getStatus()) {
      case RECEIVED ->
          this.queueRepository
              .getFirstReceived()
              .filter(received -> received.getId().equals(document.getId()))
              .map(d -> setStatus(d, IN_PREPARATION))
              .switchIfEmpty(
                  Mono.error(new BusinessException(ServiceError.QUEUE_NOT_FIRST_PROCESS)));
      case IN_PREPARATION -> Mono.just(document).map(d -> setStatus(d, READY));
      case READY -> Mono.just(document).map(d -> setStatus(d, FINISHED));
      case FINISHED ->
          Mono.error(new BusinessException(ServiceError.QUEUE_ENVOLVE_STATUS_FINISHED));
    };
  }

  private Queue setStatus(Queue queue, QueueTrackingStatus status) {
    queue.setStatus(status);
    queue.setTimestampCurrentStatus(LocalDateTime.now());
    return queue;
  }
}
