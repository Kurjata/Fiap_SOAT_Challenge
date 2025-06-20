package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.QueueMapper;
import com.fiap.outbound.repository.MongoQueueRepository;
import com.fiap.dto.order.Order;
import com.fiap.dto.queue.Queue;
import com.fiap.dto.queue.QueueFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fiap.repository.QueueRepository;

@Component
@RequiredArgsConstructor
public class QueueRepositoryImpl extends BaseRepositoryImpl implements QueueRepository {
  private final QueueMapper mapper;
  private final MongoQueueRepository repository;

  public Mono<Queue> save(Queue queue) {
    return Mono.just(queue).map(mapper::toDocument).flatMap(repository::save).map(mapper::toQueue);
  }

  public Mono<Queue> findById(String id) {
    return this.validateAndCreateMongoId(id).flatMap(repository::findById).map(mapper::toQueue);
  }

  public Mono<Queue> create(Order order) {
    return Mono.just(order).map(mapper::create).map(mapper::toQueue);
  }

  public Mono<Queue> getFirstReceived() {
    return repository.getFirstReceived().map(mapper::toQueue);
  }

  public Flux<Queue> getByFilter(QueueFilter filter) {
    return repository.getByFilter(filter).map(mapper::toQueue);
  }

  public Mono<Long> getCountByFilter(QueueFilter filter) {
    return repository.getCountByFilter(filter);
  }
}
