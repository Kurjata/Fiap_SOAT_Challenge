package repository;

import dto.order.Order;
import dto.queue.Queue;
import dto.queue.QueueFilter;
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
