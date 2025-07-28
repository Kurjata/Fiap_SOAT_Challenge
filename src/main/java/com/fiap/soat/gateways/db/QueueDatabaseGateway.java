package com.fiap.soat.gateways.db;

import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QueueDatabaseGateway extends DataBaseGateway<QueueDTO> {
  Flux<QueueDTO> findByFilter(FilterDTO filter);

  Mono<Long> countByFilter(FilterDTO filter);

  Mono<QueueDTO> findFirstReceived();

  Mono<QueueDTO> create(OrderDTO order);
}
