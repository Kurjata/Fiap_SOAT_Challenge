package com.fiap.soat.api.dao;

import com.fiap.soat.api.dao.repository.OrderRepository;
import com.fiap.soat.entities.dto.FilterDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.gateways.db.OrderDatabaseGateway;
import com.fiap.soat.gateways.mapper.OrderGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderGatewayDAO implements OrderDatabaseGateway {

  private final OrderRepository repository;
  private final OrderGatewayMapper mapper;

  @Override
  public Flux<OrderDTO> findByFilter(FilterDTO filter) {
    return repository.getByFilter(filter).map(mapper::toDTO);
  }

  @Override
  public Mono<Long> countByFilter(FilterDTO filter) {
    return repository.getCountByFilter(filter);
  }

  @Override
  public Mono<OrderDTO> findById(String id) {
    return this.stringToId(id).flatMap(repository::findById).map(mapper::toDTO);
  }

  @Override
  public Mono<OrderDTO> save(OrderDTO order) {
    return Mono.just(order).map(mapper::toDocument).flatMap(repository::save).map(mapper::toDTO);
  }
}
