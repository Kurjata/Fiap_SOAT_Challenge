package com.fiap.repository;

import com.fiap.dto.order.Order;
import com.fiap.dto.queue.Queue;
import com.fiap.dto.queue.QueueFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QueueRepository {
  Mono<Queue> save(Queue queue);

  Mono<Queue> findById(String id);

  Mono<Queue> create(Order order);

  Mono<Queue> getFirstReceived();

  Flux<Queue> getByFilter(QueueFilter filter);

  Mono<Long> getCountByFilter(QueueFilter filter);
}
