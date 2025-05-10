package com.fiap.soat.service;

import static com.fiap.soat.model.enums.OrderStatus.CREATED;
import static com.fiap.soat.model.enums.OrderStatus.PAID;
import static com.fiap.soat.model.enums.OrderStatus.WAITING_FOR_PAYMENT;

import com.fiap.soat.model.dto.charge.ChargeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class ChargeService {

  private OrderService orderService;

  public Mono<ChargeDTO> create(ChargeDTO dto) {
    return Mono.just(dto.getOrderId())
        .flatMap(this.orderService::getById)
        .filter(order -> CREATED.equals(order.getStatus()))
        .filter(Predicate.not(order -> order.getProducts().isEmpty()))
        .map(
            order -> {
              order.setStatus(WAITING_FOR_PAYMENT);
              return order;
            })
        .flatMap(this.orderService::save)
        .thenReturn(dto);
  }

  public Mono<ChargeDTO> payment(ChargeDTO dto) {
    return Mono.just(dto.getOrderId())
        .flatMap(this.orderService::getById)
        .filter(order -> WAITING_FOR_PAYMENT.equals(order.getStatus()))
        .map(
            order -> {
              order.setStatus(PAID);
              return order;
            })
        .flatMap(this.orderService::save)
            //.flatMap() TODO: Criar inserção na fila no banco de dados
        .thenReturn(dto);
  }
}
