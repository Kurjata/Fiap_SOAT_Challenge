package com.fiap.soat.api.dao;

import java.util.Objects;

import com.fiap.soat.api.dao.repository.QueueRepository;
import com.fiap.soat.entities.document.queue.QueueDocument;
import com.fiap.soat.entities.document.queue.QueueHistoryDocument;
import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.gateways.db.QueueDatabaseGateway;
import com.fiap.soat.gateways.mapper.QueueGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class QueueGatewayDAO implements QueueDatabaseGateway {
  private final QueueRepository repository;
  private final QueueGatewayMapper mapper;

  @Override
  public Flux<QueueDTO> findByFilter(FilterDTO filter) {
    return repository.getByFilter(filter).map(mapper::toDTO);
  }

  @Override
  public Mono<Long> countByFilter(FilterDTO filter) {
    return repository.getCountByFilter(filter);
  }

  @Override
  public Mono<QueueDTO> findFirstReceived() {
    return repository.getFirstReceived().map(mapper::toDTO);
  }

  @Override
  public Mono<QueueDTO> create(OrderDTO order) {
    return Mono.just(order)
        .map(mapper::create)
        .map(this::addHistory)
        .flatMap(repository::save)
        .map(mapper::toDTO);
  }

  @Override
  public Mono<QueueDTO> findById(String id) {
    return this.stringToId(id).flatMap(repository::findById).map(mapper::toDTO);
  }

  @Override
  public Mono<QueueDTO> save(QueueDTO queue) {
    return Mono.just(queue)
        .map(mapper::toDocument)
        .map(this::addHistory)
        .flatMap(repository::save)
        .map(mapper::toDTO);
  }

  private QueueDocument addHistory(QueueDocument queue) {
    var lastStatus = queue.getHistory().isEmpty() ? null : queue.getHistory().getLast().getStatus();

    if (Objects.nonNull(lastStatus) && !lastStatus.equals(queue.getStatus())) {
      queue
          .getHistory()
          .add(
              QueueHistoryDocument.builder()
                  .status(queue.getStatus())
                  .timestamp(queue.getTimestampCurrentStatus())
                  .build());
    }

    return queue;
  }
}
