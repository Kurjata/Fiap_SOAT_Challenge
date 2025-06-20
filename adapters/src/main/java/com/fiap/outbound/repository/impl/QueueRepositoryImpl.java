package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.QueueMapper;
import com.fiap.outbound.repository.MongoQueueRepository;
import dto.order.Order;
import dto.queue.Queue;
import dto.queue.QueueFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.QueueRepository;

@Component
@RequiredArgsConstructor
public class QueueRepositoryImpl extends BaseRepositoryImpl implements QueueRepository {
  private final QueueMapper mapper;
  private final MongoQueueRepository repository;

  public Mono<Queue> save(Queue queue) {
    return Mono.just(queue).map(mapper::toDocument).flatMap(repository::save).map(mapper::toQueue);
  }

  public Mono<Queue> findById(String id) {
    return null;
  }

  public Mono<Queue> create(Order order) {
    return Mono.just(order).map(mapper::create).map(mapper::toQueue);
  }

  public Mono<Queue> getFirstReceived() {
    return null;
  }

  public Flux<Queue> getByFilter(QueueFilter filter) {
    return null;
  }

  public Mono<Long> getCountByFilter(QueueFilter filter) {
    return null;
  }
}
