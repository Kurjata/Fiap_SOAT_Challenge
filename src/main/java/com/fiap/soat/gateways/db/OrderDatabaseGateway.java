package com.fiap.soat.gateways.db;

import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderDatabaseGateway extends DataBaseGateway<OrderDTO> {
  Flux<OrderDTO> findByFilter(FilterDTO filter);

  Mono<Long> countByFilter(FilterDTO filter);
}
