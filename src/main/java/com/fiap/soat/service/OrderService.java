package com.fiap.soat.service;

import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;
  private OrderMapper orderMapper;

  public Mono<OrderDTO> create(OrderDTO dto) {
    return Mono.just(dto)
        .map(this.orderMapper::createToDocument)
        .flatMap(this.orderRepository::save)
        .map(this.orderMapper::toDTO);
  }
}
