package com.fiap.outbound.repository.impl;

import com.fiap.inbound.mapper.OrderMapper;
import com.fiap.outbound.repository.MongoOrderRepository;
import com.fiap.dto.order.Order;
import com.fiap.dto.order.OrderFilter;
import com.fiap.dto.order.OrderProduct;
import com.fiap.dto.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fiap.repository.OrderRepository;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl extends BaseRepositoryImpl implements OrderRepository {
  private final MongoOrderRepository repository;
  private final OrderMapper mapper;

  public Mono<Order> save(Order order) {
    return Mono.just(order).map(mapper::toDocument).flatMap(repository::save).map(mapper::toOrder);
  }

  public Mono<Order> findById(String id) {
    return this.validateAndCreateMongoId(id).flatMap(repository::findById).map(mapper::toOrder);
  }

  public Flux<Order> findByFilter(OrderFilter filter) {
    return repository.getByFilter(filter).map(mapper::toOrder);
  }

  public Mono<Long> getCountByFilter(OrderFilter filter) {
    return repository.getCountByFilter(filter);
  }

  public Mono<OrderProduct> getOrderProduct(Product product) {
    return Mono.just(product).map(mapper::toOrderProduct);
  }
}
