package com.fiap.service.impl;

import static com.fiap.enums.QueueTrackingStatus.FINISHED;
import static com.fiap.enums.QueueTrackingStatus.IN_PREPARATION;
import static com.fiap.enums.QueueTrackingStatus.READY;

import com.fiap.dto.order.Order;
import com.fiap.dto.queue.Queue;
import com.fiap.dto.queue.QueueFilter;
import com.fiap.dto.queue.QueueHistory;
import com.fiap.enums.QueueTrackingStatus;
import com.fiap.enums.ServiceError;
import com.fiap.exception.BusinessException;
import com.fiap.exception.NotFoundException;
import java.time.LocalDateTime;

import com.fiap.service.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.fiap.repository.QueueRepository;

@Service
@AllArgsConstructor
public class QueueServiceImpl implements QueueService {
  private QueueRepository queueRepository;

  public Mono<Queue> create(Order order) {
    return Mono.just(order)
        .flatMap(this.queueRepository::create)
        .map(this::addHistory)
        .flatMap(this.queueRepository::save);
  }

  public Mono<PageImpl<Queue>> getByFilter(QueueFilter filter) {
    return this.queueRepository
        .getCountByFilter(filter)
        .flatMap(
            total ->
                this.queueRepository
                    .getByFilter(filter)
                    .collectList()
                    .map(list -> new PageImpl<>(list, filter.getPageable(), total)));
  }

  public Mono<Queue> nextStatus(String id) {
    return Mono.just(id)
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
